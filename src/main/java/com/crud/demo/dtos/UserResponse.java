package com.crud.demo.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
