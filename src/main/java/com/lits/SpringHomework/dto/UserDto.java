package com.lits.SpringHomework.dto;

import com.lits.SpringHomework.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String password;
    @Builder.Default
    private User.Role role = User.Role.ROLE_USER;
}
