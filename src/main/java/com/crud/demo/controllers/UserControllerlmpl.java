package com.crud.demo.controllers;

import com.crud.demo.model.UserModel;
import com.crud.demo.service.UserServicelmpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserControllerlmpl implements UserController  {

    private final UserServicelmpl userService;
    public UserControllerlmpl(UserServicelmpl userService) {
        this.userService = userService;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public UserModel getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @Override
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel userModel){
        return userService.updateUser(id,userModel);
    }

    @Override
    public UserModel createUser(@RequestBody UserModel userModel) {
        return userService.saveUser(userModel);
    }

    @Override
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje","User " + id + " Deleted Succesfully");
        return ResponseEntity.ok(response);
    }


}
