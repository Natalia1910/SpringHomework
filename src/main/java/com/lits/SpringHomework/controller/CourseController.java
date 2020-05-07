package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.CourseDto;
import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.service.CourseService;
import com.lits.SpringHomework.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }


    @PostMapping("/course")
    public ResponseEntity<Course> createCourse(@RequestBody CourseDto courseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseDTO.getName()));
    }


    @GetMapping(value = "/course/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(courseService.getCourse(id));
    }

    @GetMapping(value = "/course/list")
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/teacher/{teacherId}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Course> getAllCoursesAssignedToTeacher(@PathVariable(name = "teacherId") Integer teacherId) {
        Teacher teacher = teacherService.getTeacher(teacherId);
        return courseService.getAllCoursesAssignedToTeacher(teacher);
    }

    @PutMapping(value = "/course/{courseId}/add_teacher/{teacherId}")
    public ResponseEntity<Course> assignTeacher(@PathVariable(value = "courseId") Integer courseId,
                                                @PathVariable(value = "teacherId") Integer teacherId) {
        Course course = courseService.getCourse(courseId);
        Teacher teacher = teacherService.getTeacher(teacherId);
        return ResponseEntity.status(HttpStatus.OK).body(courseService.assignTeacherToCourse(course, teacher));
    }

    @GetMapping("/course/teachers/{numberOfTeachers}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Course> getCoursesWithNumberOfAssignedTeachers(@PathVariable(value = "numberOfTeachers") int numberOfTeachers) {
        return courseService.getCoursesWithNumberOfAssignedTeachers(numberOfTeachers);
    }


    @DeleteMapping(value = "/course/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteCourse(@PathVariable(value = "id") Integer id) {
        courseService.deleteCourse(id);
    }
}
