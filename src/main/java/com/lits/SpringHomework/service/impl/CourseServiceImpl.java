package com.lits.SpringHomework.service.impl;

import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.repository.CourseRepository;
import com.lits.SpringHomework.repository.TeacherRepository;
import com.lits.SpringHomework.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getOne(int id) {
        return courseRepository.findOneById(id);
    }

    @Override
    public Course update(int id, String name, Teacher teacher) {
        Course course = courseRepository.findOneById(id);
        course.setName(name);
        return courseRepository.save(course);
    }

    @Override
    public void delete(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course addTeachersToCourse(int courseID, List<Teacher> teachers) {
        Course course = courseRepository.findOneById(courseID);
        course.setTeachers(teachers);
        return courseRepository.save(course);
    }
}
