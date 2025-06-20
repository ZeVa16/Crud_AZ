package com.crud.demo.controllers;


import com.crud.demo.model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("api/v1/user")
public interface UserController {

    @GetMapping
    List<UserModel> getAllUsers();

    @GetMapping("/{id}")
    UserModel getUserById(@PathVariable Long id);

    @PutMapping("/{id}")
    UserModel updateUser(@PathVariable Long id, @RequestBody UserModel userModel);

    @PostMapping
    UserModel createUser(UserModel userModel);

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id);
}
