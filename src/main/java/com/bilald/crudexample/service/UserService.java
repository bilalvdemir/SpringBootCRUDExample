package com.bilald.crudexample.service;

import java.util.List;

import com.bilald.crudexample.exception.AlreadyExistException;
import com.bilald.crudexample.exception.NotFoundException;
import com.bilald.crudexample.model.User;

public interface UserService {

    public User saveUser(User user) throws AlreadyExistException;

    public User getUser(String userId) throws NotFoundException;

    public User updateUser(User user) throws NotFoundException;

    public User deleteUser(String userName) throws NotFoundException;

    public List<User> getAllUser();

}
