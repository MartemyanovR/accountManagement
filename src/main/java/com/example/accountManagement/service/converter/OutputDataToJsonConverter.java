package com.example.accountManagement.service.converter;

import com.example.accountManagement.model.dto.OutputDataDto;
import com.example.accountManagement.service.EmployeeCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@Component
public class OutputDataToJsonConverter {

    private final EmployeeCredentialsService employeeCredentialsService;
    private static final String URL_OUTPUT_DATA =
            "http://localhost:3000/credentials";

    @Autowired
    public OutputDataToJsonConverter(EmployeeCredentialsService employeeCredentialsService) {
        this.employeeCredentialsService = employeeCredentialsService;
    }


    @PostConstruct
    public void sendOutputData() {
        OutputDataDto outputDataDto = employeeCredentialsService.chooseActionForStaffChanges();
        if(outputDataDto == null) {
            return;
        }
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL_OUTPUT_DATA, outputDataDto, OutputDataDto.class);
    }

}
