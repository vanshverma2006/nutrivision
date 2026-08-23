package com.nutrivision.controller;

import com.nutrivision.entity.User;
import com.nutrivision.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.nutrivision.dto.UserRequest;
import com.nutrivision.dto.UserResponse;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {

        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        User savedUser = userService.createUser(user);

        return UserResponse.fromUser(savedUser);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}