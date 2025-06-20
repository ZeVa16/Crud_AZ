package com.crud.demo.service;



import com.crud.demo.model.UserModel;
import com.crud.demo.repository.UserRespository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServicelmpl implements UserService {

    private final UserRespository userRespository;
    public UserServicelmpl(UserRespository userRespository) {
        this.userRespository = userRespository;
    }
    @Override
    public List<UserModel> getAllUsers() {
       return userRespository.findAll();
    }

    @Override
    public UserModel getUserById(Long id){
        return userRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserModel updateUser(Long id, UserModel userModel){
        UserModel Existing = userRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Existing.setFirstName(userModel.getFirstName());
        Existing.setLastName(userModel.getLastName());
        Existing.setEmail(userModel.getEmail());
        return userRespository.save(Existing);
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userRespository.save(userModel);
    }
    @Override
    public void deleteUser(Long id) {
        userRespository.deleteById(id);
    }
}
