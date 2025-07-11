package com.crud.demo.controllers;

import com.crud.demo.dtos.ApiResponse;
import com.crud.demo.dtos.UserRequest;
import com.crud.demo.dtos.UserResponse;
import com.crud.demo.exceptions.UserNotFoundException;
import com.crud.demo.mapper.UserMapper;
import com.crud.demo.model.UserModel;
import com.crud.demo.service.UserServicelmpl;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserControllerlmpl implements UserController  {

    private final UserServicelmpl userService;
    public UserControllerlmpl(UserServicelmpl userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        List<UserResponse> userResponses = users.stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());

        ApiResponse<List<UserResponse>> apiResponse = ApiResponse.<List<UserResponse>>builder()
                .message("Users obtained successfully")
                .data(userResponses)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id){
        UserModel user = userService.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("The user with id " + id + " does not exist"));

            UserResponse userResponse = UserMapper.toResponse(user);
            ApiResponse<UserResponse> notFoundResponse = ApiResponse.<UserResponse>builder()
                    .message("User is found")
                    .data(userResponse)
                    .status(HttpStatus.OK)
                    .build();
            return new ResponseEntity<>(notFoundResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        UserModel user = userService.getUserById(id)
                .orElseThrow(()-> new UserNotFoundException("The User with id " + id + " does not exist"));

            UserModel entity = UserMapper.toEntity(userRequest);
            UserModel updatedEntity = userService.updateUser(id, entity);
            UserResponse userResponse = UserMapper.toResponse(updatedEntity);

            ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                    .message("This user has been updated")
                    .data(userResponse)
                    .status(HttpStatus.OK)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody UserRequest userRequest) {
        UserModel entity = UserMapper.toEntity(userRequest);
        UserModel savedEntity = userService.saveUser(entity);
        UserResponse response = UserMapper.toResponse(savedEntity);

        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .message("This User has been created")
                .data(response)
                .status(HttpStatus.CREATED)
                .build();   

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<UserResponse>> deleteUser(@PathVariable Long id) {
        UserModel user = userService.getUserById(id)
                .orElseThrow(()-> new UserNotFoundException("The User with id " + id + " does not exist"));

        userService.deleteUser(id);

        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .message("This User has been deleted")
                .data(null)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
     }


}
