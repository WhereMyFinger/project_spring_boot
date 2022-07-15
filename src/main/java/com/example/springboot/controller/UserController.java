package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import com.example.springboot.entities.User;
import com.example.springboot.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getAllUsers(Model model,
                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                              @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        Sort sortable = null;
        if(sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if(sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);

        model.addAttribute("users", userService.getAllUser(pageable));
        return "user-index";
    }

    @GetMapping("/user/search")
    public String getListUser(Model model,
                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                              @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
                              @RequestParam String name) {
        Sort sortable = null;
        if(sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if(sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);

        model.addAttribute("users", userService.getListUser(pageable, name));
        return "user-index";
    }

    @GetMapping("/user/add_form")
    public String showAddNewForm(Model model, User user) {
        model.addAttribute("user", user);
        return "add-new-form";
    }

    @PostMapping("/user/add_user")
    public String addNewUser(@Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "add-new-form";
        }
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("/user/update")
    public String showUpdateform(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "update-form";
    }

    @PutMapping("/user/update")
    public String updateUser(@Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "update-form";
        }
        userService.saveUser(user);
        return "redirect:/user";
    }

    @DeleteMapping("/user/delete")
    public String deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}
