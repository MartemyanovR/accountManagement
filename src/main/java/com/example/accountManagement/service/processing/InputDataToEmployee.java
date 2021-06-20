package com.example.accountManagement.service.processing;

import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.Status;
import com.example.accountManagement.model.dto.InputDataDto;
import org.springframework.stereotype.Component;

@Component
public class InputDataToEmployee {


    public Employee fillEmployeeForCreate(InputDataDto inputDataDto) {
        Employee employee = new Employee();
        employee.setRole(inputDataDto.getRole());
        employee.setFio(inputDataDto.getFio());
        employee.setPost(inputDataDto.getPost());
        employee.setStatus(Status.ACTIVE);

        return employee;
    }

    public Employee fillEmployeeForUpdate(InputDataDto inputDataDto) {
        Employee employee = new Employee();
        employee.setId(inputDataDto.getId());
        employee.setRole(inputDataDto.getRole());
        employee.setFio(inputDataDto.getFio());
        employee.setPost(inputDataDto.getPost());

        return employee;
    }

    public Employee fillEmployeeForUpdateStatus(InputDataDto inputDataDto) {
        Employee employee = new Employee();
        employee.setId(inputDataDto.getId());

        return employee;
    }

 }
