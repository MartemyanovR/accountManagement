package com.example.accountManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Класс-сущность для хранения сотрудников в БД
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
