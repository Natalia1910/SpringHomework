package com.lits.SpringHomework.service;


import com.lits.SpringHomework.dto.CourseDto;
import com.lits.SpringHomework.dto.TeacherDto;
import com.lits.SpringHomework.util.StatusOfCourse;

import java.util.List;

public interface TeacherService {

    Long create(TeacherDto teacherDto);

    TeacherDto findOneById(Long id);

    List<TeacherDto> findAll();

    List<TeacherDto> findAllTeachersAssignedToCourse(CourseDto courseDto);

    List<CourseDto> findFilteredCourses(StatusOfCourse statusOfCourse);

    List<CourseDto> findCoursesThatLast(int numberOfDays);

    void delete(Long teacherId);

}
