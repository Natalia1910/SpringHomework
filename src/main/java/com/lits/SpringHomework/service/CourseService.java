package com.lits.SpringHomework.service;

import com.lits.SpringHomework.dto.CourseDto;
import com.lits.SpringHomework.dto.TeacherDto;

import java.util.List;

public interface CourseService {
    Long create(CourseDto courseDto);

    CourseDto findOneById(Long id);

    List<CourseDto> findAll();

    List<CourseDto> findAllCoursesAssignedToTeacher(TeacherDto teacherDto);

    CourseDto assignTeacherToCourse(CourseDto courseDto, TeacherDto teacherDto);

    CourseDto unassignTeacherFromCourse(CourseDto courseDto, TeacherDto teacherDto);

    List<CourseDto> findCoursesWithNumberOfAssignedTeachers(int numberOfTeachers);

    void delete(Long courseId);

    List<CourseDto> findAllCoursesAssignedToStudent(Long studentId);

    List<CourseDto> findAllCoursesWithAssignedTeacherAndStudent(Long teacherId, Long studentId);

    CourseDto assignStudentToCourse(Long courseId, Long studentId);

    CourseDto unassignStudentFromCourse(Long courseId, Long studentId);
}

