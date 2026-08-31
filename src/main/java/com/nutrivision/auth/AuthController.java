package com.nutrivision.auth;

import com.nutrivision.dto.LoginRequest;
import com.nutrivision.dto.LoginResponse;
import com.nutrivision.dto.UserRequest;
import com.nutrivision.dto.UserResponse;
import com.nutrivision.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(
            @Valid @RequestBody UserRequest request
    ) {

        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        User savedUser = authService.register(user);

        return UserResponse.fromUser(savedUser);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
}