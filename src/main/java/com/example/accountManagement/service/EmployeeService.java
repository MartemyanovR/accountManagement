package com.example.accountManagement.service;

import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.Status;

import java.util.List;

public interface EmployeeService {

    Employee getById(Integer id);

    void save(Employee employee);

    List<Employee> getAll();

    void update(String role,String fio, String post, Integer id);

    void updateStatus(Status status, Integer id);

}
