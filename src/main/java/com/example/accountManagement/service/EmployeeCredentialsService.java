package com.example.accountManagement.service;

import com.example.accountManagement.model.Credentials;
import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.Status;
import com.example.accountManagement.model.dto.InputDataDto;
import com.example.accountManagement.model.dto.OutputDataDto;
import com.example.accountManagement.service.processing.CredentialsToOutputDate;
import com.example.accountManagement.service.processing.EmployeeToCredentials;
import com.example.accountManagement.service.processing.InputDataToEmployee;
import com.example.accountManagement.service.converter.JsonToInputDataConverter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class EmployeeCredentialsService {

    private final JsonToInputDataConverter jsonToInputDataConverter;
    private final EmployeeServiceImpl employeeService;
    private final CredentialsServiceImpl credentialsService;
    private final CredentialsToOutputDate credentialsToOutputDate;
    private final EmployeeToCredentials employeeToCredentials;
    private final InputDataToEmployee inputDataToEmployee;


    @Autowired
    public EmployeeCredentialsService(JsonToInputDataConverter jsonToInputDataConverter,
                                      EmployeeServiceImpl employeeService,
                                      CredentialsServiceImpl credentialsService,
                                      CredentialsToOutputDate credentialsToOutputDate,
                                      EmployeeToCredentials employeeToCredentials,
                                      InputDataToEmployee inputDataToEmployee) {
        this.jsonToInputDataConverter = jsonToInputDataConverter;
        this.employeeService = employeeService;
        this.credentialsService = credentialsService;
        this.credentialsToOutputDate = credentialsToOutputDate;
        this.employeeToCredentials = employeeToCredentials;
        this.inputDataToEmployee = inputDataToEmployee;
    }

    public OutputDataDto chooseActionForStaffChanges() {
        @NonNull final InputDataDto inputDataDto = jsonToInputDataConverter.getInputDataRest();
        final Employee employee;
        final Credentials credentials;

        if(inputDataDto.getType() == 1) {
            employee = getAndSaveToEmployee(inputDataDto);
            credentials = createCredentials(employee);
            return credentialsToOutputDate.fillOutputDate(credentials);
        }
        else if(inputDataDto.getType() == 3) {
            employee = getAndUpdateToEmployee(inputDataDto);
            if(employee.getStatus() == Status.BLOCKED) {
                log.info("Employee was blocked");
                return null;
            }
            credentials = updateCredentials(employee);
            return credentialsToOutputDate.fillOutputDate(credentials);
        }
        else {
            getAndUpdateStatusToEmployee(inputDataDto);
            log.info("Employee was blocked");
            return null;
        }
    }

    private Employee getAndSaveToEmployee(InputDataDto inputDataDto) {

        final Employee employee;
        employee = inputDataToEmployee.fillEmployeeForCreate(inputDataDto);
        employeeService.save(employee);

        return employee;
    }

    private Employee getAndUpdateToEmployee(InputDataDto inputDataDto) {

        final Employee employee;
        employee = inputDataToEmployee.fillEmployeeForUpdate(inputDataDto);
        employeeService.update(employee.getRole(), employee.getFio(), employee.getPost(), employee.getId());

        return employee;
    }

    private Employee getAndUpdateStatusToEmployee(InputDataDto inputDataDto) {

        final Employee employee;
        employee = inputDataToEmployee.fillEmployeeForUpdateStatus(inputDataDto);
        if(employeeService.getById(employee.getId()).getStatus() == Status.ACTIVE) {
            employeeService.updateStatus(Status.BLOCKED, employee.getId());
        }
        return employee;
    }

    private Credentials createCredentials(Employee employee) {

        final Credentials credentials;
        credentials = employeeToCredentials.fillCredentials(employee);
        credentialsService.save(credentials);

        return credentials;
    }

    private Credentials updateCredentials(Employee employee) {

        final Credentials credentials;
        credentials = employeeToCredentials.fillCredentials(employee);
        credentialsService.update(credentials.getUserName(), credentials.getPassword(), credentials.getId());

        return credentials;
    }


}
