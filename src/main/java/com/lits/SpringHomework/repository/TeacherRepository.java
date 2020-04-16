package com.lits.SpringHomework.repository;

import com.lits.SpringHomework.model.Teacher;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer>, PagingAndSortingRepository<Teacher, Integer> {

    Teacher findOneById(Integer id);

    List<Teacher> findAll();

    List<Teacher> findAll(Sort sort);
}
