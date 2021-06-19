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
@ToString
public class Credentials {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "employee"))
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "passwrd")
    private String password;

    @OneToOne(mappedBy = "credentials", cascade = CascadeType.ALL)
    private Employee employee;

}
