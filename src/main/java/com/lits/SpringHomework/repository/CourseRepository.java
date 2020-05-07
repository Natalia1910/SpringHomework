package com.lits.SpringHomework.repository;

import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAllByTeachersContaining(Teacher teacher);

    List<Course> findAllByStartDateGreaterThan(Date date);

    List<Course> findAllByEndDateLessThan(Date date);

    List<Course> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date startDateComparisonDate, Date endDateComparisonDate);

    @Query("SELECT c FROM Course c WHERE function('TIMESTAMPDIFF', DAY, c.startDate, c.endDate) = ?1")
    List<Course> findAllByDateDiffBetweenStartDateAndEndDateEqualTo(int numberOfDays);


}

