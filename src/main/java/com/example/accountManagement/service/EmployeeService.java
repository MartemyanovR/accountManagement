package com.example.accountManagement.service;

import com.example.accountManagement.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getById(Integer id);

    void save(Employee employee);

    void delete(Integer id);

    List<Employee> getAll();

    int update(String role,String fio, String post, Integer id);



}
