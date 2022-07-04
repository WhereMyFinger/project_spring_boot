package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.springboot.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Page<User> findAllByUserNameContaining(Pageable pageable, @Param("userName") String userName);
}
