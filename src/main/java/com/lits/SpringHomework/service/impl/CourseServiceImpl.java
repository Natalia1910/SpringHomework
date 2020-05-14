package com.lits.SpringHomework.service.impl;

import com.lits.SpringHomework.dto.CourseDto;
import com.lits.SpringHomework.dto.TeacherDto;
import com.lits.SpringHomework.exception.CourseNotFoundException;
import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.repository.CourseRepository;
import com.lits.SpringHomework.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long create(CourseDto courseDto) {
        return courseRepository.save(modelMapper.map(courseDto, Course.class)).getId();
    }

    @Override
    public CourseDto findOneById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException());
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    public List<CourseDto> findAll() {
        List<Course> courses = (List<Course>) courseRepository.findAll();
        return courses.stream().map(c -> modelMapper.map(c, CourseDto.class)).collect(toList());
    }

    @Override
    public List<CourseDto> findAllCoursesAssignedToTeacher(TeacherDto teacherDto) {
        return null;
    }

    @Override
    public CourseDto assignTeacherToCourse(CourseDto courseDto, TeacherDto teacherDto) {
        return null;
    }

    @Override
    public CourseDto unassignTeacherFromCourse(CourseDto courseDto, TeacherDto teacherDto) {
        return null;
    }

    @Override
    public List<CourseDto> findCoursesWithNumberOfAssignedTeachers(int numberOfTeachers) {
        return null;
    }

    @Override
    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<CourseDto> findAllCoursesAssignedToStudent(Long studentId) {
        return null;
    }

    @Override
    public List<CourseDto> findAllCoursesWithAssignedTeacherAndStudent(Long teacherId, Long studentId) {
        return null;
    }

    @Override
    public CourseDto assignStudentToCourse(Long courseId, Long studentId) {
        return null;
    }

    @Override
    public CourseDto unassignStudentFromCourse(Long courseId, Long studentId) {
        return null;
    }
}
