package com.lits.SpringHomework.model;

import lombok.Data;
import lombok.Getter;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany
    private List<Teacher> teachers;
    @ManyToMany
    private List<Student> students;

}
