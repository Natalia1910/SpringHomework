package com.lits.SpringHomework.service;

import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;

import java.util.Date;
import java.util.List;

public interface CourseService {

    Course createCourse(String courseName);

    Course createCourseWithStartAndEndDates(String courseName, Date startDate, Date endDate);

    Course updateCourse(Course course);

    Course getCourse(Integer id);

    List<Course> getAllCourses();

    List<Course> getAllCoursesAssignedToTeacher(Teacher teacher);

    Course assignTeacherToCourse(Course course, Teacher teacher);

    Course unassignTeacherFromCourse(Course course, Teacher teacher);

    List<Course> getCoursesWithNumberOfAssignedTeachers(int numberOfTeachers);

    void deleteCourse(Integer courseId);

}

