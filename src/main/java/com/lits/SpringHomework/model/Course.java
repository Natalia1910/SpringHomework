package com.lits.SpringHomework.model;

import lombok.Data;
import lombok.Getter;


import javax.persistence.*;
import java.util.Date;
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
    private Date startDate;
    private Date endDate;

    @ManyToMany
    private List<Teacher> teachers;
    @ManyToMany
    private List<Student> students;

}
