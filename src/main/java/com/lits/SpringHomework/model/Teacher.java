package com.lits.SpringHomework.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private int age;
    private String number;

    @ManyToMany(targetEntity = Course.class)
    private List<Course> courses = new ArrayList<>();


}
