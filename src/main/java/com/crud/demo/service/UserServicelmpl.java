package com.crud.demo.service;



import com.crud.demo.model.UserModel;
import com.crud.demo.repository.UserRespository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServicelmpl {

    private final UserRespository userRespository;
    public UserServicelmpl(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    public List<UserModel> getAllUsers() {
       return userRespository.findAll();
    }

    public UserModel saveUser(UserModel userModel) {
        return userRespository.save(userModel);
    }
    public void deleteUser(Long id) {
        userRespository.deleteById(id);
    }
}
