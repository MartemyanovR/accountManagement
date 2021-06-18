package com.example.accountManagement.repository;

import com.example.accountManagement.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update Employee e set e.role = :role , e.fio = :fio, e.post = :post " +
            "where e.id = :id")
    int updateEmployee(@Param("role") String role, @Param("fio") String fio,
                                             @Param("post")  String post, @Param("post") Integer id);



}
