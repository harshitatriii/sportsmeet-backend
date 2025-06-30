package com.sportsmeet.backend.service;

import com.sportsmeet.backend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
}
