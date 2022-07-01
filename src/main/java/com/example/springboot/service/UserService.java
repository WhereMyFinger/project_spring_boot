package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entities.User;
import com.example.springboot.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    public List<User> getListUser(String name) {
        return userRepository.findAllByUserNameContaining(name);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    /*public void updateUser(int id, User user) {
        User userFromDB = userRepository.findById(id).get();
        userFromDB.setUserName(user.getUserName());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setBirthDay(user.getBirthDay());
        userRepository.save(userFromDB);
    }*/

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
