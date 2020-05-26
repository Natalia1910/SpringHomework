package com.lits.SpringHomework.service;


import com.lits.SpringHomework.dto.TeacherDto;

import java.util.List;

public interface TeacherService {

    Long create(TeacherDto teacherDto);

    TeacherDto findOneById(Long id);

    List<TeacherDto> findAll();

    List<TeacherDto> findAllTeachersAssignedToCourse(Long courseId);

    void delete(Long teacherId);

    TeacherDto getTeacherByPhoneNumber(String number);

}
