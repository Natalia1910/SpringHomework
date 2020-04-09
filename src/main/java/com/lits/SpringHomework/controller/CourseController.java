package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.CourseDto;
import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.service.CourseService;
import com.lits.SpringHomework.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping("/course/{id}")
    public Course getById(@PathVariable(name = "id") Integer id) {
        return courseService.getOne(id);
    }

    @PostMapping("/course")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Course create(@RequestBody CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        return courseService.create(course);
    }

    @PutMapping("/course/{id}")
    public Course update(@PathVariable(name = "id") Integer id, @RequestBody CourseDto courseDto) {
        return courseService.update(id, courseDto.getName(), teacherService.getOne(courseDto.getTeacherID()));
    }

    @DeleteMapping("/course/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void delete(@PathVariable(name = "id") Integer id) {
        courseService.delete(id);
    }

    @GetMapping("/courses")
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @PutMapping("/course/{id}/teachers")
    public Course addTeachers(@PathVariable(name = "id") Integer courseId, @RequestBody CourseDto courseDto) {
        List<Teacher> teachers = new ArrayList<>();
        for (Integer teacherId : courseDto.getTeachersID()) {
            teachers.add(teacherService.getOne(teacherId));
        }
        return courseService.addTeachersToCourse(courseId, teachers);
    }
}
