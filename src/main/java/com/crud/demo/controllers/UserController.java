package com.crud.demo.controllers;

import com.crud.demo.model.UserModel;
import com.crud.demo.service.UserServicelmpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserServicelmpl userService;
    public UserController(UserServicelmpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel userModel) {
        return userService.saveUser(userModel);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}
