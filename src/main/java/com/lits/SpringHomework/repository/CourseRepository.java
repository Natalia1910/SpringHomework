package com.lits.SpringHomework.repository;

import com.lits.SpringHomework.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    Course findOneById(Integer id);
    List<Course> findAll();
}
