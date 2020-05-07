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
}

