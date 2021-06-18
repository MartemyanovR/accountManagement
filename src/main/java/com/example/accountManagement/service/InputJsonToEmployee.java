package com.example.accountManagement.service;

import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.dto.InputDataDto;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if(inputDataDto.getType() == 1) {
            employeeService.save(
                    fillEmployee(inputDataDto, employee)
                     );

            return employee;
        }
        else if(inputDataDto.getType() == 2) {
            /*
             */
            return null;
        }
        else if(inputDataDto.getType() == 3) {
            employee = employeeService.getById(inputDataDto.getId());

            return fillEmployee(inputDataDto, employee);
        }
        return employee;
    }

    private Employee fillEmployee(InputDataDto inputDataDto, Employee employee) {
        employee.setRole(inputDataDto.getRole());
        employee.setFio(inputDataDto.getFio());
        employee.setPost(inputDataDto.getPost());
        return employee;
    }
 }
