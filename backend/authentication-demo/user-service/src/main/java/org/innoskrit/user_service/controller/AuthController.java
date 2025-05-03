package org.innoskrit.user_service.controller;

import org.innoskrit.auth_lib.JwtTokenUtil;
import org.innoskrit.user_service.dto.AuthRequest;
import org.innoskrit.user_service.dto.AuthResponse;
import org.innoskrit.user_service.dto.CreateUserRequest;
import org.innoskrit.user_service.dto.UserDto;
import org.innoskrit.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        System.out.println("authRequest: " + request);

        UserDto user = userService.getByEmail(request.getEmail());
        System.out.println("user: " + user);
        String token = jwtTokenUtil.generateToken(user.getId(), user.getRoles());
        return new AuthResponse(token);
    }

    @PostMapping("/sign-up")
    public UserDto signUp(@RequestBody CreateUserRequest request) {
        return userService.insertUser(request);
    }
}