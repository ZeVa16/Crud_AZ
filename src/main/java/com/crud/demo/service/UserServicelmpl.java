package com.crud.demo.service;



import com.crud.demo.model.UserModel;
import com.crud.demo.repository.UserRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
    public Optional<UserModel> getUserById(Long id){
        return userRespository.findById(id);
    }

    @Override
    public UserModel updateUser(Long id, UserModel userModel){
        if( id != null ){
            Optional<UserModel> existingUserModel = userRespository.findById(id);
            if(existingUserModel.isPresent()){
                UserModel existing = existingUserModel.get();
                existing.setUsername(userModel.getUsername());
                existing.setFirstName(userModel.getFirstName());
                existing.setLastName(userModel.getLastName());
                existing.setEmail(userModel.getEmail());
                return userRespository.save(existing);
            }
        }
        userModel.setId(id);
        return userRespository.save(userModel);
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
