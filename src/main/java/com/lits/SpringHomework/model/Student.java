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
    @Column(name = "name")
    private String studentName;
    @Column(name = "surname")
    private String studentSurname;
    @Column(name = "age")
    private int age;

    @ManyToMany
    private List<Course> courses;


}
