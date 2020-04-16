package com.lits.SpringHomework.service;

import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;

import java.util.List;


public interface TeacherService {

    Teacher createTeacher(String firstName, String lastName, Integer age);

    Teacher getTeacher(Integer teacherId);

    List<Teacher> getAllTeachers();

    List<Course> getAllCoursesAssignedToTeacher(Teacher teacher);

    void deleteTeacher(Integer teacherId);

}
