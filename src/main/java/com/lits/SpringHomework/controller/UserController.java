package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.RoleDto;
import com.lits.SpringHomework.dto.UserDto;
import com.lits.SpringHomework.service.RoleService;
import com.lits.SpringHomework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String ping() {

        return "Hello";
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Long singUp(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping("/{userId}")
    public UserDto assignRole(@PathVariable Long userId, @RequestBody List<Long> roleIds) {
        List<RoleDto> roleDto = roleService
                .findRolesWithIds(roleIds)
                .stream()
                .peek(roleDTO -> roleDTO.setUserId(userId))
                .map(roleService::update)
                .collect(Collectors.toList());
        UserDto userDto = userService.findById(userId);
        userDto.setRole(roleDto);
        return userService.update(userDto);
    }
}
