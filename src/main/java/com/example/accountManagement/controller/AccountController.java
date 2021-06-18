package com.example.accountManagement.controller;

import com.example.accountManagement.model.Employee;
import com.example.accountManagement.service.EmployeeServiceImpl;
import com.example.accountManagement.service.InputJsonToEmployee;
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

    @Autowired
    public AccountController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getEmployee(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "view/employees";
    }



}
