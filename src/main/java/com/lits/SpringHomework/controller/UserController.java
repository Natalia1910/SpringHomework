package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.UserDto;
import com.lits.SpringHomework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String ping() {

        return "Hello";
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') || #userDTO.role.toString() == 'ROLE_USER'")
    public Long singUp(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }
}
