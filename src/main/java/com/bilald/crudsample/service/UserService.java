package com.bilald.crudsample.service;

import com.bilald.crudsample.dto.request.UpdateUserRequest;
import com.bilald.crudsample.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getUserByUsername(String username);

    User getUserById(String id);

    Page<User> getAllUsers(Pageable pageable);

    User createUser(User user);

    User updateUser(String id, UpdateUserRequest request);

    void deleteUser(String username);
}
