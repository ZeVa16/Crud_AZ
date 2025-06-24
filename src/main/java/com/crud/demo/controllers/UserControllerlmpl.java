package com.crud.demo.controllers;

import com.crud.demo.dtos.ApiResponse;
import com.crud.demo.dtos.UserRequest;
import com.crud.demo.dtos.UserResponse;
import com.crud.demo.mapper.UserMapper;
import com.crud.demo.model.UserModel;
import com.crud.demo.service.UserServicelmpl;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserControllerlmpl implements UserController  {

    private final UserServicelmpl userService;
    public UserControllerlmpl(UserServicelmpl userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id){
        Optional<UserModel> user = userService.getUserById(id);

        if(user.isEmpty()){
            ApiResponse<UserResponse> notFoundResponse = ApiResponse.<UserResponse>builder()
                    .message("User with id "+ id + " not Found")
                    .data(null)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
        }else {
            UserResponse userResponse = UserMapper.toResponse(user.get());
            ApiResponse<UserResponse> notFoundResponse = ApiResponse.<UserResponse>builder()
                    .message("User is found")
                    .data(userResponse)
                    .status(HttpStatus.OK)
                    .build();
            return new ResponseEntity<>(notFoundResponse, HttpStatus.OK);
        }


    }

    @Override
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        Optional<UserModel> user = userService.getUserById(id);
        if(user.isEmpty()){
            ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                    .message("This user does not exist")
                    .data(null)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }else {
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
        userService.deleteUser(id);

        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .message("This User has been deleted")
                .data(null)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
     }


}
