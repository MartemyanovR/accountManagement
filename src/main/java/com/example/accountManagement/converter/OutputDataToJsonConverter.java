package com.example.accountManagement.converter;

import com.example.accountManagement.model.dto.OutputDataDto;
import com.example.accountManagement.service.EmployeeCredentialsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Класс для передачи данных в виде JSON-обьекта
 */
@Slf4j
@Component
public class OutputDataToJsonConverter {

    private  EmployeeCredentialsService employeeCredentialsService;

    private static final String URL_OUTPUT_DATA =
            "http://localhost:3000/credentials";

    @Autowired
    public void setEmployeeCredentialsService(EmployeeCredentialsService employeeCredentialsService) {
        this.employeeCredentialsService = employeeCredentialsService;
    }

    /**
     * Метод отправляет обработанные данные
     * на внешний сервис
     */
    public void sendOutputData() {
        final Optional<OutputDataDto> outputDataDtoOptional =
                employeeCredentialsService.chooseActionForStaffChanges();
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(outputDataDtoOptional.isPresent()) {
            restTemplate.postForObject(URL_OUTPUT_DATA,
                    new HttpEntity(outputDataDtoOptional.get(), headers) , OutputDataDto.class);
            log.info("Data was sent successfully");
        } else {
            log.warn("Account has been locked, or incorrect data is present.");
        }
    }

}
