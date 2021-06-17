package com.example.accountManagement.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
@Getter
@Setter
@ToString
public class Credentials {

    @Id
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "passwrd")
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
