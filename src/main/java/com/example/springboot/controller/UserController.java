package com.example.springboot.controller;

import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.entities.User;

@Controller


public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "user-index";
    }

    @GetMapping("/user/search")
    public String getListUser(@RequestParam String name, Model model) {
        model.addAttribute("users", userService.getListUser(name));
        return "user-index";
    }

    @GetMapping("/user/add_form")
    public String showAddNewForm(Model model, User user) {
        model.addAttribute("user", user);
        return "add-new-form";
    }

    @PostMapping("/user/add_user")
    public String addNewUser(User user) {
        //User user = new User();
        userService.saveUser(user);
        //model.addAttribute("user", user);
        return "redirect:/user";
    }

    @GetMapping("/user/update")
    public String showUpdateform(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "update-form";
    }

    @PutMapping("/user/update")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

   /* @PostMapping("/user/save")
    public String save(User user) {
        userRepository.save(user);
        return "redirect:/user";
    }*/

    /*@GetMapping("/user/delete")
    public String showDeleteAlert(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "delete-alert";
    }*/

    @DeleteMapping("/user/delete")
    public String deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}
