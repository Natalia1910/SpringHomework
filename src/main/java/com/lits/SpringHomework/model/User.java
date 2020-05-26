package com.lits.SpringHomework.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    @Fetch(FetchMode.SELECT)
    public List<Role> role = new ArrayList<>();

}
