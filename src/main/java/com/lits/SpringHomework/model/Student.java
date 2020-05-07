package com.lits.SpringHomework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String studentName;
    private String studentSurname;
    private String nameOfGroup;
    private int age;

    @ManyToMany
    private List<Course> courses;


}
