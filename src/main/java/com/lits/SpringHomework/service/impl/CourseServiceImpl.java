package com.lits.SpringHomework.service.impl;

import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.repository.CourseRepository;
import com.lits.SpringHomework.repository.TeacherRepository;
import com.lits.SpringHomework.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public Course createCourse(String course) {
        return courseRepository.save(new Course(course));
    }

    @Override
    public Course createCourseWithStartAndEndDates(String name, Date startDate, Date endDate) {
        return courseRepository.save(new Course(name, startDate, endDate));
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourse(Integer id) {
        return courseRepository.findOneById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course assignTeacherToCourse(Course course, Teacher teacher) {
        course.getTeachers().add(teacher);
        return courseRepository.save(course);
    }

    @Override
    public Course unassignTeacherFromCourse(Course course, Teacher teacher) {
        course.getTeachers().remove(teacher);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getCoursesWithNumberOfAssignedTeachers(int numberOfTeachers) {
        return getAllCourses().stream().filter(course -> course.getTeachers().size() == numberOfTeachers)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> getAllCoursesAssignedToTeacher(Teacher teacher) {
        return courseRepository.findAllByTeachersContaining(teacher);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        courseRepository.deleteById(courseId);
    }
}
