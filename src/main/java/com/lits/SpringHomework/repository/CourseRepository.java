package com.lits.SpringHomework.repository;

import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

    Course findOneById(Integer id);

    List<Course> findAll();

    List<Course> findAllByTeachersContaining(Teacher teacher);

    List<Course> findAllByStartDateGreaterThan(Date date);

    List<Course> findAllByEndDateLessThan(Date date);

    List<Course> findAllByStartDateLessThanAndEndDateGreaterThan(Date startDate, Date endDate);


}

