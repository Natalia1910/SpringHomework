package com.lits.SpringHomework.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CustomLITSRepository <T, ID> extends Repository<T, ID> {
    Slice<T> findAll(Pageable pageable);
    Optional<T> getOne(ID id);
}
