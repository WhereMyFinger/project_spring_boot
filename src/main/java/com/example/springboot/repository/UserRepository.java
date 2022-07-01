package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.springboot.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAllByUserNameContaining(@Param("userName") String userName);
}
