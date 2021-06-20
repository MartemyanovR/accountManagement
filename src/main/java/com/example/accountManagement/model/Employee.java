package com.example.accountManagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role")
    private String role;

    @Column(name = "fio")
    private String fio;

    @Column(name = "post")
    private String post;

    @Column(name = "status_empl")
    @Enumerated(EnumType.STRING)
    private Status status;

}
