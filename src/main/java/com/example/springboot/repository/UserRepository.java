package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.springboot.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where upper(u.userName) like concat('%', upper(:userName), '%')")
    List<User> findAllByNameContaining(@Param("userName") String userName);

    @Query("select u from User u where u.id = :id")
    User findUserById(@Param("id") int id);
}
