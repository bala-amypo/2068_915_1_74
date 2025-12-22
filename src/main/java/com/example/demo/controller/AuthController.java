package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    
    public AuthController(UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    
    @PostMapping("/register")
    public User register(@RequestBody AuthRequest request) {

        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                null 
        );

        
        return userService.register(user);
    }

   
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

    User user = userService.findByEmail(request.getEmail());

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new IllegalArgumentException("Invalid email or password");
    }

    String token = jwtUtil.generateToken(
            user.getEmail(),
            user.getRole(),
            user.getId()
    );

    return new AuthResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getRole()
    );
}

}
