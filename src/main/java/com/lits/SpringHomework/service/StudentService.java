package com.lits.SpringHomework.service;

import com.lits.SpringHomework.dto.StudentDto;

import java.util.List;

public interface StudentService {

    Long create(StudentDto studentDto);

    StudentDto findOneById(Long id);

    List<StudentDto> findAll();

    void delete(Long studentId);
}
