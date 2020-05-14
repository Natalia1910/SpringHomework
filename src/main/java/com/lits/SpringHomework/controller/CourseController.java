package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.CourseDto;
import com.lits.SpringHomework.service.CourseService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody CourseDto courseDto) {
        return courseService.create(courseDto);
    }

    @GetMapping("/course/{courseId}")
    public CourseDto findCourseById(@PathVariable Long courseId){
        return courseService.findOneById(courseId);
    }

    @GetMapping
    public List<CourseDto> findAll(){
        return courseService.findAll();
    }

    @DeleteMapping("/course/delete/{courseId}")
    public void delete(@PathVariable Long courseId){
        courseService.delete(courseId);
    }
}
