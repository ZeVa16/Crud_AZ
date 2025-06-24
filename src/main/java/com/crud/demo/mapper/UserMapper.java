package com.crud.demo.mapper;

import com.crud.demo.dtos.UserRequest;
import com.crud.demo.dtos.UserResponse;
import com.crud.demo.model.UserModel;

public class UserMapper {

    public static UserModel toEntity(UserRequest userRequest) {
        return UserModel.builder()
                .username(userRequest.getUsername())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

    public static UserResponse toResponse(UserModel userModel) {
        return new UserResponse(
                userModel.getUsername(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getEmail()
        );
    }
}
