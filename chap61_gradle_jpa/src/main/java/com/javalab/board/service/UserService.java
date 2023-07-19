package com.javalab.board.service;

import java.util.List;

import com.javalab.board.entity.User;



public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User createUser(User user);
    User updateUser(Integer id, User updatedUser);
    boolean deleteUser(Integer id);
}
