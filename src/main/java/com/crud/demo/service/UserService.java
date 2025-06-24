package com.crud.demo.service;

import com.crud.demo.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserModel> getAllUsers();
    Optional<UserModel> getUserById(Long id);
    UserModel updateUser(Long id, UserModel userModel);
    UserModel saveUser(UserModel userModel);
    void deleteUser(Long id);
}
