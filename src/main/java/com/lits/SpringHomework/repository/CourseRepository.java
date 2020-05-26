package com.lits.SpringHomework.repository;

import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Student;
import com.lits.SpringHomework.model.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findOneById(Long courseId);

    List<Course> findAllByTeachersContaining(Teacher teacher);

    List<Course> findAllByStudentsContaining(Student student);

    List<Course> findAllByTeachersContainingAndStudentsContaining(Teacher teacher, Student student);

    List<Course> findAllByStartDateGreaterThan(LocalDate localDate);

    List<Course> findAllByEndDateLessThan(LocalDate localDate);

    List<Course> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDateComparisonDate,
                                                                           LocalDate endDateComparisonDate);

    @Query("SELECT c FROM Course c WHERE function('TIMESTAMPDIFF', DAY, c.startDate, c.endDate) = ?1")
    List<Course> findAllByDateDiffBetweenStartDateAndEndDateEqualTo(int numberOfDays);


}

