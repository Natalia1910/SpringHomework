package com.lits.SpringHomework.service.impl;
import com.lits.SpringHomework.dto.TeacherDto;
import com.lits.SpringHomework.exception.TeacherNotFoundException;
import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.repository.CourseRepository;
import com.lits.SpringHomework.repository.TeacherRepository;
import com.lits.SpringHomework.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private  final ModelMapper modelMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Long create(TeacherDto teacherDto) {
         return teacherRepository.save(modelMapper.map(teacherDto, Teacher.class)).getId();
    }

    @Override
    public TeacherDto findOneById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException());
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public List<TeacherDto> findAll() {
        List<Teacher> teachers = (List<Teacher>) teacherRepository.findAll();
        return teachers.stream().map(t -> modelMapper.map(t, TeacherDto.class)).collect(toList());
    }

    @Override
    public List<TeacherDto> findAllTeachersAssignedToCourse(Long courseId) {
        Course course = (Course) courseRepository.findOneById(courseId);
        List<Teacher> teacher = teacherRepository.findAllByCoursesContaining(course);
        return teacher.stream().map(t -> modelMapper.map(t, TeacherDto.class)).collect(toList());
    }

    @Override
    public void delete(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public TeacherDto getTeacherByPhoneNumber(String number) {
        Teacher teacher = (Teacher) teacherRepository.findOneByNumber(number);
        return modelMapper.map(teacher, TeacherDto.class);
    }
}


