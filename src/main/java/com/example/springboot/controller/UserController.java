package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.entities.User;
import com.example.springboot.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user-index";
    }

    @GetMapping("/user/search")
    public String getListUser(@RequestParam String name, Model model) {
        model.addAttribute("users", userRepository.findAllByNameContaining(name));
        return "user-index";
    }

    @GetMapping("/user/add_user")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "form";
    }

    @GetMapping("/user/update/{id}")
    public String updateUser(@PathVariable int id,
                             Model model) {
        User user = userRepository.findUserById(id);
            model.addAttribute("user", user);
        return "form";
    }

    @PostMapping("/user/save")
    public String save(User user) {
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }
}
