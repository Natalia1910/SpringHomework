package com.lits.SpringHomework.service.impl;

import com.lits.SpringHomework.dto.StudentDto;
import com.lits.SpringHomework.model.Student;
import com.lits.SpringHomework.repository.StudentRepository;
import com.lits.SpringHomework.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long create(StudentDto studentDto) {
        return studentRepository.save(modelMapper.map(studentDto, Student.class)).getStudentId();
    }

    @Override
    public StudentDto findOneById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return students.stream().map(s -> modelMapper.map(s, StudentDto.class)).collect(toList());
    }

    @Override
    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public int getNumberOfCoursesAssignedToStudent(Long studentId) {
        return 0;
    }
}
