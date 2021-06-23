package com.example.accountManagement.service;

import com.example.accountManagement.model.Credentials;
import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.Status;
import com.example.accountManagement.model.dto.InputDataDto;
import com.example.accountManagement.model.dto.OutputDataDto;
import com.example.accountManagement.service.processing.CredentialsToOutputDate;
import com.example.accountManagement.service.processing.EmployeeToCredentials;
import com.example.accountManagement.service.processing.InputDataToEmployee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Класс-сервис выполняет основную бизнес-логику сервиса.
 *
 */
@Slf4j
@Service
public class EmployeeCredentialsService {

    private final EmployeeServiceImpl employeeService;
    private final CredentialsServiceImpl credentialsService;
    private final CredentialsToOutputDate credentialsToOutputDate;
    private final EmployeeToCredentials employeeToCredentials;
    private final InputDataToEmployee inputDataToEmployee;


    @Autowired
    public EmployeeCredentialsService(EmployeeServiceImpl employeeService,
                                      CredentialsServiceImpl credentialsService,
                                      CredentialsToOutputDate credentialsToOutputDate,
                                      EmployeeToCredentials employeeToCredentials,
                                      InputDataToEmployee inputDataToEmployee) {
        this.employeeService = employeeService;
        this.credentialsService = credentialsService;
        this.credentialsToOutputDate = credentialsToOutputDate;
        this.employeeToCredentials = employeeToCredentials;
        this.inputDataToEmployee = inputDataToEmployee;
    }

    /**
     * Метод для реализации необходимого действия в соответствии
     * со значением типа(type) объекта.
     * @return Optional<OutputDataDto> содержащий готовый для передачи
     * на внешний сервис объект OutputDataDto, либо пустой Optional
     * если статус сотрудника был Blocked или при некорректных данных
     */
    public Optional<OutputDataDto> chooseActionForStaffChanges(InputDataDto inputDataDto) {
        final Employee employee;
        final Credentials credentials;

        if(inputDataDto.getType() == 1) {
            employee = getAndSaveToEmployee(inputDataDto);
            credentials = createCredentials(employee);
            return Optional.of(credentialsToOutputDate.fillOutputDate(credentials));
        }
        else if(inputDataDto.getType() == 3) {
            Optional<Employee> employeeOptional = getAndUpdateToEmployee(inputDataDto);
            if(!employeeOptional.isPresent() ) {
                log.info("Employee was blocked");
                return Optional.empty();
            }
            credentials = updateCredentials(employeeOptional.get());

            return Optional.of(credentialsToOutputDate.fillOutputDate(credentials));
        }
        else if(inputDataDto.getType() == 2){
            getAndUpdateStatusToEmployee(inputDataDto);
            return Optional.empty();
        }
        return Optional.empty();
    }


    private Employee getAndSaveToEmployee(InputDataDto inputDataDto) {

        final Employee employee;
        employee = inputDataToEmployee.fillEmployeeForCreate(inputDataDto);
        employeeService.save(employee);

        return employee;
    }


    private Optional<Employee> getAndUpdateToEmployee(InputDataDto inputDataDto) {

        final Employee employee;
        employee = inputDataToEmployee.fillEmployeeForUpdate(inputDataDto);
        if(employeeService.getById(employee.getId()).getStatus() == Status.BLOCKED) {

              return Optional.empty();
        }
        employeeService.update(employee.getRole(), employee.getFio(), employee.getPost(), employee.getId());

        return Optional.of(employee);
    }

    private void getAndUpdateStatusToEmployee(InputDataDto inputDataDto) {

        final Employee employee;
        employee = inputDataToEmployee.fillEmployeeForUpdateStatus(inputDataDto);
        if(employeeService.getById(employee.getId()).getStatus() == Status.ACTIVE) {
            employeeService.updateStatus(Status.BLOCKED, employee.getId());
        }

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
