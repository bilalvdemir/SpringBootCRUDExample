package com.bilald.crudsample.service;

import com.bilald.crudsample.dto.request.UpdateUserRequest;
import com.bilald.crudsample.exception.UserAlreadyExistsException;
import com.bilald.crudsample.exception.UserNotFoundException;
import com.bilald.crudsample.mapper.UserMapper;
import com.bilald.crudsample.model.User;
import com.bilald.crudsample.monitoring.UserMetrics;
import com.bilald.crudsample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMetrics userMetrics;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    @Cacheable(value = "users", key = "#username", unless = "#result == null")
    public User getUserByUsername(String username) {
        log.debug("Fetching user from database: {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    @Cacheable(value = "users", key = "#id", unless = "#result == null")
    public User getUserById(String id) {
        log.debug("Fetching user by ID from database: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Cacheable(value = "users", key = "'all:' + #pageable.pageNumber + ':' + #pageable.pageSize")
    public Page<User> getAllUsers(Pageable pageable) {
        log.debug("Fetching all users with pagination: {}", pageable);
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true)
    })
    public User createUser(User user) {
        log.info("Creating new user: {}", user.getUsername());

        // Check if username exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername());
        }

        // Check if email exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered: " + user.getEmail());
        }

        // Note: Password hashing would be done here if security was enabled
        // user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        userMetrics.incrementUserCreated();

        return savedUser;
    }

    @Override
    @Transactional
    @Caching(
            put = @CachePut(value = "users", key = "#result.username"),
            evict = @CacheEvict(value = "users", allEntries = true)
    )
    public User updateUser(String id, UpdateUserRequest request) {
        log.info("Updating user: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userMapper.updateEntity(request, user);

        User updatedUser = userRepository.save(user);
        userMetrics.incrementUserUpdated();

        return updatedUser;
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "users", key = "#username"),
            @CacheEvict(value = "users", allEntries = true)
    })
    public void deleteUser(String username) {
        log.info("Deleting user: {}", username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        userRepository.delete(user);
        userMetrics.incrementUserDeleted();
    }
}
