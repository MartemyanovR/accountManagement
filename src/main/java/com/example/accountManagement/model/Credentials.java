package com.example.accountManagement.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
@Getter
@Setter
public class Credentials {

    @Id
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "passwrd")
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn
    private Employee employee;

}
