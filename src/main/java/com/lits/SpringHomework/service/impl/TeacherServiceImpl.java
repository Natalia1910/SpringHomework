package com.lits.SpringHomework.service.impl;


import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.repository.TeacherRepository;
import com.lits.SpringHomework.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public Teacher create(String name, String surname, int age) {
        return teacherRepository.save(new Teacher(name, surname, age));
    }


    @Override
    public Teacher getOne(int id) {
        return teacherRepository.findOneById(id);
    }

    @Override
    public Teacher update(int id, String name, String surname) {
        Teacher teacher = teacherRepository.findOneById(id);
        teacher.setName(name);
        teacher.setSurname(surname);
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(int id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAll(String sortBy) {
        List<Teacher> teachers;
        if (("age").equals(sortBy)) {
            Sort sortByAge = Sort.by(sortBy).descending();
            teachers = teacherRepository.findAll(sortByAge);
        } else {
            teachers = teacherRepository.findAll();
        }
        return teachers;
    }
}


