package com.lits.SpringHomework.repository;

import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Teacher> findOneById(Long teacherId);
    List<Teacher> findAllByCoursesContaining(Course course);
    List<Teacher> findOneByNumber(String number);
}
