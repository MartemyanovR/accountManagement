package com.example.accountManagement.service;

import com.example.accountManagement.model.Credentials;
import com.example.accountManagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsConverter {

    private final InputJsonToEmployee inputJsonToEmployee;

    @Autowired
    public CredentialsConverter(InputJsonToEmployee inputJsonToEmployee) {
        this.inputJsonToEmployee = inputJsonToEmployee;
    }

    public void createCredentials(Credentials credentials) {
        Employee employee = inputJsonToEmployee.getEmployee();
        credentials.setUserName(employee.getFio().replaceAll("\\s+", ""));
        credentials.setPassword(generatePassword());

    }

    private String generatePassword() {

        return null;
    }
}
