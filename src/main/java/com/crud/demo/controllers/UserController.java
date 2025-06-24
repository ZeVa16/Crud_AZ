package com.crud.demo.controllers;


import com.crud.demo.dtos.ApiResponse;
import com.crud.demo.dtos.UserRequest;
import com.crud.demo.dtos.UserResponse;
import com.crud.demo.model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("api/v1/user")
public interface UserController {

    @GetMapping
    ResponseEntity<List<UserModel>> getAllUsers();

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest);

    @PostMapping
    ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody UserRequest userRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<UserResponse>> deleteUser(@PathVariable Long id);
}
