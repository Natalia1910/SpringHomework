package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.TeacherDto;
import com.lits.SpringHomework.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody TeacherDto teacherDto) {
        return teacherService.create(teacherDto);
    }

    @GetMapping("/teacher/{teacherId}")
    public TeacherDto findTeacherById(@PathVariable Long teacherId) {
        return teacherService.findOneById(teacherId);
    }

    @GetMapping("teacher/all")
    public List<TeacherDto> findAll() {
        return teacherService.findAll();
    }

    @DeleteMapping("teacher/delete/{teacherId}")
    public void delete(@PathVariable Long teacherId){
        teacherService.delete(teacherId);
    }
}
