package org.innoskrit.user_service.controller;

import org.innoskrit.auth_lib.CustomUserPrincipal;
import org.innoskrit.user_service.dto.UserDto;
import org.innoskrit.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/details")
    public UserDto getUserDetails(@AuthenticationPrincipal CustomUserPrincipal userPrincipal) {
        return userService.getById(userPrincipal.getUserId());
    }
}
