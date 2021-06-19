package com.example.accountManagement.service;

import com.example.accountManagement.model.Credentials;
import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.dto.InputDataDto;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InputJsonToEmployee {

    private final InputDataProcessing inputDataProcessing;
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public InputJsonToEmployee(InputDataProcessing inputDataProcessing,
                               EmployeeServiceImpl employeeService) {

        this.inputDataProcessing = inputDataProcessing;
        this.employeeService = employeeService;
    }

    public Employee getEmployee() {
       @NonNull final InputDataDto inputDataDto = inputDataProcessing.getInputDataRest();
       Employee employee = new Employee();
       Credentials credentials = new Credentials();

        if(inputDataDto.getType() == 1) {
            employee = fillEmployee(inputDataDto, employee, credentials);
            return employeeService.save(employee);
        }
        else if(inputDataDto.getType() == 2) {
            /*
                   блокировка аккаунта
             */
            return null;
        }
        else  {
            employee = fillEmployee(inputDataDto, employee, credentials);
            employeeService.update(employee.getRole(), employee.getFio(),
                    employee.getPost(), inputDataDto.getId());
            return employeeService.getById(inputDataDto.getId());
        }
    }

    private Employee fillEmployee(InputDataDto inputDataDto, Employee employee, Credentials credentials) {
        employee.setRole(inputDataDto.getRole());
        employee.setFio(inputDataDto.getFio());
        employee.setPost(inputDataDto.getPost());
        credentials.setEmployee(employee);
        return employee;
    }
 }
