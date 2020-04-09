package com.lits.SpringHomework.service;

import com.lits.SpringHomework.model.Teacher;

import java.util.List;


public interface TeacherService {

    Teacher create(String name, String surname, int age);

    Teacher getOne(int id);

    Teacher update(int id, String name, String surname);

    void delete(int id);

    List<Teacher> getAll(String sortBy);

}
