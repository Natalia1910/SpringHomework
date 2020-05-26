package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.CourseDto;
import com.lits.SpringHomework.exception.IllegalCourseSearchException;
import com.lits.SpringHomework.service.CourseService;

import com.lits.SpringHomework.util.StatusOfCourse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/app/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_COURSES')")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody CourseDto courseDto) {
        return courseService.create(courseDto);
    }

    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAuthority('READ_COURSES')")
    public CourseDto findCourseById(@PathVariable Long courseId){
        return courseService.findOneById(courseId);
    }

    @GetMapping("/course/all")
    @PreAuthorize("hasAuthority('READ_COURSES')")
    public List<CourseDto> findAll(){
        return courseService.findAll();
    }

    @DeleteMapping("/course/delete/{courseId}")
    @PreAuthorize("hasAuthority('DELETE_COURSES')")
    public void delete(@PathVariable Long courseId){
        courseService.delete(courseId);
    }

    @GetMapping("/course/teacher/{teacherId}")
    @PreAuthorize("hasAuthority('READ_TEACHERS')")
    public List<CourseDto> findAllCoursesAssignedToTeacher(@PathVariable Long teacherId) {
        return courseService.findAllCoursesAssignedToTeacher(teacherId);
    }

    @GetMapping("/course/student/{studentId}")
    @PreAuthorize("hasAuthority('READ_STUDENTS')")
    public List<CourseDto> findAllCoursesAssignedToStudent(@PathVariable Long studentId) {
        return courseService.findAllCoursesAssignedToStudent(studentId);
    }

    @GetMapping("/course/teacher/{teacherId}/student/{studentId}")
    @PreAuthorize("hasAuthority('READ_COURSES')")
    public List<CourseDto> findAllCoursesWithAssignedTeacherAndStudent(@PathVariable Long teacherId, Long studentId) {
        return courseService.findAllCoursesWithAssignedTeacherAndStudent(teacherId, studentId);
    }

    @PutMapping("/course/{courseId}/add_teacher/{teacherId}")
    @PreAuthorize("hasAuthority('ASSIGN_TEACHERS_TO_COURSES')")
    public CourseDto assignTeacher(@PathVariable Long courseId, Long teacherId) {
        return courseService.assignTeacherToCourse(courseId, teacherId);
    }

    @PutMapping("/course/{courseId}/add_student/{studentId}")
    @PreAuthorize("hasAuthority('ASSIGN_STUDENTS_TO_COURSES')")
    public CourseDto assignStudent(@PathVariable Long courseId, Long studentId) {
        return courseService.assignStudentToCourse(courseId, studentId);
    }

    @PutMapping(path = "/course/{courseId}/remove_teacher/{teacherId}")
    @PreAuthorize("hasAuthority('UNASSIGN_TEACHERS_FROM_COURSES')")
    public CourseDto unassignTeacher(@PathVariable Long courseId, Long teacherId) {
        return courseService.unassignTeacherFromCourse(courseId, teacherId);
    }

    @PutMapping(path = "/course/{courseId}/remove_student/{studentId}")
    @PreAuthorize("hasAuthority('UNUSSIGN_STUDENTS_FROM_COURSES')")
    public CourseDto unassignStudent(@PathVariable Long courseId, Long studentId) {
        return courseService.unassignStudentFromCourse(courseId, studentId);
    }

    @GetMapping(path = "/course/filter/{sort}")
    @PreAuthorize("hasAuthority('READ_COURSES')")
    public List<CourseDto> getFilteredCourses(@PathVariable String sort) {

        StatusOfCourse statusOfCourse;

        switch (sort) {
            case "not_started":
                statusOfCourse = StatusOfCourse.NOT_STARTED;
                break;
            case "finished":
                statusOfCourse = StatusOfCourse.FINISHED;
                break;
            case "in_process":
                statusOfCourse = StatusOfCourse.IN_PROCESS;
                break;
            default:
                throw new IllegalCourseSearchException("Passed filtering criteria is not supported.");

        }
        return courseService.getFilteredCourses(statusOfCourse);
    }

    @GetMapping(path = "/course/duration/{numberOfDays}")
    @PreAuthorize("hasAuthority('READ_COURSES')")
    public List<CourseDto> getCoursesThatLast(@PathVariable int numberOfDays) {
        return courseService.getCoursesThatLast(numberOfDays);
    }
}
