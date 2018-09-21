package com.bilald.crudexample.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bilald.crudexample.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    public User findById(String userId);
    
    public List<User> findAll();

    public User deleteByName(String userName);

    public User findByUsername(String userName);
}
