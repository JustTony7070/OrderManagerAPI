package com.example.javaspringrestapi.controllers;

import com.example.javaspringrestapi.entities.User;
import com.example.javaspringrestapi.repos.UserRepo;
import com.example.javaspringrestapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    private final UserRepo userRepo;

    public UserController(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userRepo.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        user.setId(null);
        user.setCreatedAt(null);
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
    }
}
