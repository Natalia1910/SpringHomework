package com.lits.SpringHomework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class TeacherDto {
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Name cannot be null")
    private String surname;

    @Min(value = 16, message = "Age should not be less than 16")
    @PositiveOrZero(message = "Age should be more than 0")
    private int age;

    @NotBlank(message = "Please, enter your phone number")
    @Pattern(regexp = "(^$|[0-9]{10})")
    @Column(unique = true)
    private String number;

}
