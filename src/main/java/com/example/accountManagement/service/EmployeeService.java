package com.example.accountManagement.service;

import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.Status;

import java.util.List;

public interface EmployeeService {

    Employee getById(Integer id);

    Employee save(Employee employee);

    List<Employee> getAll();

    int update(String role,String fio, String post, Integer id);

    int updateStatus(Status status, Integer id);

}
