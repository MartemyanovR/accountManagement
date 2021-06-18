package com.example.accountManagement.service;

import com.example.accountManagement.model.Employee;
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
        log.info("IN EmployeeServiceImpl getById{}", id);
        return employeeRepository.findById(id).get();
    }

    @Override
    public void save(Employee employee) {
        log.info("IN EmployeeServiceImpl save{}", employee);
        employeeRepository.save(employee);
    }

    @Override
    public int  update(String role,String fio, String post, Integer id) {
        log.info("IN EmployeeServiceImpl update{}", id);
        return employeeRepository.updateEmployee(role, fio, post, id);
    }

    @Override
    public List<Employee> getAll() {
        log.info("IN EmployeeServiceImpl getAllEmployees");
        return (List<Employee>)employeeRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        log.info("IN EmployeeServiceImpl delete{}", id);
        employeeRepository.deleteById(id);
    }

}
