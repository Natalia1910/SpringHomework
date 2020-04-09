package com.lits.SpringHomework.service;

import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;

import java.util.List;

public interface CourseService {

    Course create(Course course);

    Course getOne(int id);

    Course update(int id, String name, Teacher teacher);

    void delete(int id);

    List<Course> getAll();

    Course addTeachersToCourse(int courseID, List<Teacher> teachers);

}

