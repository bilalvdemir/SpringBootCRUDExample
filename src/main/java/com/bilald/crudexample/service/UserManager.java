package com.bilald.crudexample.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bilald.crudexample.exception.AlreadyExistException;
import com.bilald.crudexample.exception.NotFoundException;
import com.bilald.crudexample.model.User;
import com.bilald.crudexample.repository.UserRepository;
import com.bilald.crudexample.utils.Utils;

@Service
public class UserManager implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserManager.class);

    UserRepository              userRepository;

    public UserManager(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws AlreadyExistException {
        LOG.debug("[saveUser] User Manager saveUser procces started. Params: user ->{}", user);
        User response = userRepository.findByUsername(user.getUsername());
        if (response == null) {
            response = userRepository.save(user);
        } else {
            throw new AlreadyExistException(Utils.getUsernameAlreadyExistException(user.getUsername()));
        }
        LOG.debug("[saveUser] User Manager saveUser procces finished. Params: response ->{}", response);
        return response;
    }

    @Override
    public User getUser(String userName) throws NotFoundException {
        LOG.debug("[getUser] User Manager saveUser procces started. Params: userName ->{}", userName);
        User response = userRepository.findByUsername(userName);
        if (response == null) {
            throw new NotFoundException(Utils.getUserNotFoundException(userName));
        }
        LOG.debug("[getUser] User Manager saveUser procces finished. Params: response ->{}", response);
        return response;
    }

    @Override
    public User updateUser(User user) throws NotFoundException {
        LOG.debug("[updateUser] User Manager saveUser procces started. Params: user ->{}", user);
        User response = userRepository.findByUsername(user.getUsername());
        if (response == null) {
            throw new NotFoundException(Utils.getUserNotFoundException(user.getUsername()));
        }
        response = userRepository.save(user);
        LOG.debug("[updateUser] User Manager saveUser procces finished. Params: response ->{}", response);
        return response;
    }

    @Override
    public User deleteUser(String userName) throws NotFoundException {
        LOG.debug("[deleteUser] User Manager saveUser procces started. Params: userName ->{}", userName);
        User response = userRepository.findByUsername(userName);
        if (response == null) {
            throw new NotFoundException(Utils.getUserNotFoundException(userName));
        }
        response = userRepository.deleteByName(userName);
        LOG.debug("[deleteUser] User Manager saveUser procces finished. Params: response ->{}", response);
        return response;
    }

    @Override
    public List<User> getAllUser() {
        LOG.debug("[getAllUser] User Manager saveUser procces started.");
        List<User> response = userRepository.findAll();
        LOG.debug("[getAllUser] User Manager saveUser procces finished. Params: response ->{}", response);
        return response;
    }

}
