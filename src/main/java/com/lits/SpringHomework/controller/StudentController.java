package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.StudentDto;
import com.lits.SpringHomework.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody StudentDto studentDto) {
        return studentService.create(studentDto);
    }


    @GetMapping("/student/{studentId}")
    public StudentDto findTeacherById(@PathVariable Long studentId) {
        return studentService.findOneById(studentId);
    }

    @GetMapping("student/all")
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }

    @DeleteMapping("/student/delete/{studentId}")
    public void delete(@PathVariable Long studentId){
        studentService.delete(studentId);
    }
}

