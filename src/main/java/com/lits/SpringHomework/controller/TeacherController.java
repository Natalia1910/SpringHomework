package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.TeacherDto;
import com.lits.SpringHomework.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/app/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_TEACHERS')")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody TeacherDto teacherDto) {
        return teacherService.create(teacherDto);
    }

    @GetMapping("/teacher/{teacherId}")
    @PreAuthorize("hasAuthority('READ_TEACHERS')")
    public TeacherDto findTeacherById(@PathVariable Long teacherId) {
        return teacherService.findOneById(teacherId);
    }

    @GetMapping("teacher/all")
    @PreAuthorize("hasAuthority('READ_TEACHERS')")
    public List<TeacherDto> findAll() {
        return teacherService.findAll();
    }

    @DeleteMapping("teacher/delete/{teacherId}")
    @PreAuthorize("hasAuthority('DELETE_TEACHERS')")
    public void delete(@PathVariable Long teacherId){
        teacherService.delete(teacherId);
    }

    @GetMapping("/teacher/number/{number}")
    @PreAuthorize("hasAuthority('READ_TEACHERS')")
    public TeacherDto getTeacherByPhoneNumber(@PathVariable String number) {
        return teacherService.getTeacherByPhoneNumber(number);
    }

    @GetMapping("/teacher/course/{id}")
    @PreAuthorize("hasAuthority('READ_COURSES')")
    public List<TeacherDto> findAllTeachersAssignedToCourse(@PathVariable Long id) {
        return teacherService.findAllTeachersAssignedToCourse(id);
    }
}
