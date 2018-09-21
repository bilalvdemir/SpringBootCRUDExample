package com.bilald.crudexample.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bilald.crudexample.exception.AlreadyExistException;
import com.bilald.crudexample.exception.NotFoundException;
import com.bilald.crudexample.model.User;
import com.bilald.crudexample.service.UserManager;
import com.bilald.crudexample.service.UserService;

@RestController
@RequestMapping("/api/1.0")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserManager.class);

    UserService                 userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public List<User> handleGetAllUser() {
        LOG.debug("[handleGetAllUser] User Controller Retrieve All User procces started.");
        List<User> result = userService.getAllUser();
        LOG.debug("[handleGetAllUser] User Manager Retrieve All User procces finished.", result);
        return result;
    }

    @GetMapping(value = "/user/{userId}")
    public User handleGetUser(@PathVariable("userId") String userId) throws NotFoundException {
        LOG.debug("[handleGetUser] User Controller Retrieve User procces started. Params: userId ->{}", userId);
        User result = userService.getUser(userId);
        LOG.debug("[handleGetUser] User Manager Retrieve User procces finished. Params: result ->{}", result);
        return result;
    }

    @PostMapping(value = "/user")
    public User handleSaveUser(@Valid @RequestBody User user) throws AlreadyExistException {
        LOG.debug("[handleSaveUser] User Controller Save User procces started. Params: user ->{}", user);
        User result = userService.saveUser(user);
        LOG.debug("[handleSaveUser] User Manager Save User procces finished. Params: result ->{}", result);
        return result;
    }

    @PutMapping(value = "/user")
    public User handleUpdateUser(@Valid @RequestBody User user) throws NotFoundException {
        LOG.debug("[handleUpdateUser] User Controller Update User procces started. Params: user ->{}", user);
        User result = userService.updateUser(user);
        LOG.debug("[handleUpdateUser] User Manager Update User procces finished. Params: result ->{}", result);
        return result;
    }

    @DeleteMapping(value = "/user/{userId}")
    public User handleDeleteUser(@PathVariable("userId") String userName) throws NotFoundException {
        LOG.debug("[handleDeleteUser] User Controller Update User procces started. Params: userName ->{}", userName);
        User result = userService.deleteUser(userName);
        LOG.debug("[handleDeleteUser] User Manager Update User procces finished. Params: result ->{}", result);
        return result;
    }
}
