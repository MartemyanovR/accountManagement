package com.example.accountManagement.service;

import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.Status;
import com.example.accountManagement.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getById(Integer id) {
        log.info("IN EmployeeServiceImpl getById: {}", id);
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee save(Employee employee) {
        log.info("IN EmployeeServiceImpl save: {}", employee);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        log.info("IN EmployeeServiceImpl getAll");
        return (List<Employee>)employeeRepository.findAll();
    }

    @Override
    public int  update(String role,String fio, String post, Integer id) {
        log.info("IN EmployeeServiceImpl update: {}, {}, {}, {}", role, fio, post, id);
        return employeeRepository.updateEmployee(role, fio, post, id);
    }

    @Override
    public int  updateStatus(Status status, Integer id) {
        log.info("IN EmployeeServiceImpl update: {},{}", status, id);
        return employeeRepository.updateStatusEmployee(status, id);
    }

}
