package com.example.accountManagement.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Класс на основании которого в таблицу БД credentials
 * сохраняются учетные данные сотрудников.
 */
@Entity
@Table(name = "credentials")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
