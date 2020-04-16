package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.TeacherDto;
import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/teacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDto teacherDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacherDTO.getName(),
                teacherDTO.getSurname(), teacherDTO.getAge()));
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(teacherService.getTeacher(id));
    }

    @GetMapping("/teacher/list")
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }


    @DeleteMapping("/teacher/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteTeacher(@PathVariable(name = "id") Integer id) {
        teacherService.deleteTeacher(id);
    }
}
