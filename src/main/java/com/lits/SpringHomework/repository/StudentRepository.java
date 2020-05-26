package com.lits.SpringHomework.repository;

import com.lits.SpringHomework.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findOneById (Long id);
}
