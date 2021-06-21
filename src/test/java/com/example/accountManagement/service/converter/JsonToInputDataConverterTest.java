package com.example.accountManagement.service.converter;

import com.example.accountManagement.model.dto.InputDataDto;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class JsonToInputDataConverterTest {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void getInputData() {
        ResponseEntity<InputDataDto> inputDataDtoResponseEntity =
                restTemplate.exchange("http://localhost:3000/employees", HttpMethod.GET, null,
                        new ParameterizedTypeReference<InputDataDto>() {});
        InputDataDto inputDataDto = inputDataDtoResponseEntity.getBody();

        assertThat(HttpStatus.OK, is(inputDataDtoResponseEntity.getStatusCode()));
        assertThat((int)inputDataDto.getType(), anyOf(equalTo(1),equalTo(2),equalTo(3)));
        assertThat(inputDataDto.getId(), anyOf(equalTo(null), isA(Integer.class)));
        assertThat(inputDataDto, is(notNullValue()));

    }
}