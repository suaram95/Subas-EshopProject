package com.example.subaseshopproject.service;

import com.example.subaseshopproject.model.User;
import com.example.subaseshopproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findByUserName(String email){
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findUserById(long id){
        return userRepository.findById(id);
    }
}
