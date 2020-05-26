package com.lits.SpringHomework.service;

import com.lits.SpringHomework.dto.CourseDto;
import com.lits.SpringHomework.util.StatusOfCourse;

import java.util.List;

public interface CourseService {

    Long create(CourseDto courseDto);

    CourseDto findOneById(Long id);

    List<CourseDto> findAll();

    List<CourseDto> getFilteredCourses(StatusOfCourse statusOfCourse);

    List<CourseDto> findAllCoursesAssignedToTeacher(Long teacherId);

    CourseDto assignTeacherToCourse(Long courseId, Long teacherId);

    CourseDto unassignTeacherFromCourse(Long courseId, Long teacherId);

    List<CourseDto> getCoursesThatLast(int numberOfDays);

    void delete(Long courseId);

    CourseDto update (CourseDto courseDto);

    List<CourseDto> findAllCoursesAssignedToStudent(Long studentId);

    List<CourseDto> findAllCoursesWithAssignedTeacherAndStudent(Long teacherId, Long studentId);

    CourseDto assignStudentToCourse(Long courseId, Long studentId);

    CourseDto unassignStudentFromCourse(Long courseId, Long studentId);
}

