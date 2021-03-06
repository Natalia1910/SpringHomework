package com.lits.SpringHomework.dto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String password;
    public List<RoleDto> role = new ArrayList<>();
}
