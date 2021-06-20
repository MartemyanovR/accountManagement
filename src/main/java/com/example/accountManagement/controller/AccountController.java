package com.example.accountManagement.controller;

import com.example.accountManagement.model.Credentials;
import com.example.accountManagement.model.Employee;
import com.example.accountManagement.service.CredentialsServiceImpl;
import com.example.accountManagement.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("accounts")
public class AccountController {


    private final EmployeeServiceImpl employeeService;
    private final CredentialsServiceImpl credentialsService;

    @Autowired
    public AccountController(EmployeeServiceImpl employeeService,
                             CredentialsServiceImpl credentialsService) {
        this.employeeService = employeeService;
        this.credentialsService = credentialsService;

    }

    @GetMapping
    public String getEmployee(Model model) {
        List<Employee> employees = employeeService.getAll();
        List<Credentials> credentials = credentialsService.getAll();
        model.addAttribute("employees", employees);
        model.addAttribute("credentials", credentials);
        return "view/employees";
    }



}
