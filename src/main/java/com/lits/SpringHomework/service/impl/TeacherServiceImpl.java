package com.lits.SpringHomework.service.impl;


import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.repository.TeacherRepository;
import com.lits.SpringHomework.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher createTeacher(String firstName, String lastName, Integer age) {
        return teacherRepository.save(new Teacher(firstName, lastName, age));
    }


    @Override
    public Teacher getTeacher(Integer teacherId) {
        return teacherRepository.findOneById(teacherId);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Course> getAllCoursesAssignedToTeacher(Teacher teacher) {
        return teacherRepository.findOneById(teacher.getId()).getCourses();
    }

    @Override
    public void deleteTeacher(Integer teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}


