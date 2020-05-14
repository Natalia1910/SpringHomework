package com.lits.SpringHomework.dto;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String studentName;
    private String studentSurname;
    private int age;
}
