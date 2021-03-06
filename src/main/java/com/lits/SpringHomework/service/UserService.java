package com.lits.SpringHomework.service;

import com.lits.SpringHomework.dto.UserDto;

public interface UserService {
    UserDto findByUsername(String name);
    Long create(UserDto userDto);
    UserDto findById (Long id);
    UserDto update (UserDto userDto);
}
