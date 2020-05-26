package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.StudentDto;
import com.lits.SpringHomework.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_STUDENTS')")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody StudentDto studentDto) {
        return studentService.create(studentDto);
    }


    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAuthority('READ_STUDENTS')")
    public StudentDto findById(@PathVariable Long studentId) {
        return studentService.findOneById(studentId);
    }

    @GetMapping("student/all")
    @PreAuthorize("hasAuthority('READ_STUDENTS')")
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }

    @DeleteMapping("/student/delete/{studentId}")
    @PreAuthorize("hasAuthority('DELETE_STUDENTS')")
    public void delete(@PathVariable Long studentId){
        studentService.delete(studentId);
    }
}

