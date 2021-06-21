package com.example.accountManagement.service.converter;

import com.example.accountManagement.model.Credentials;
import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.Status;
import com.example.accountManagement.model.dto.InputDataDto;
import com.example.accountManagement.model.dto.OutputDataDto;
import com.example.accountManagement.repository.CredentialsRepository;
import com.example.accountManagement.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OutputDataToJsonConverterTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CredentialsRepository credentialsRepository;

    RestTemplate restTemplate = new RestTemplate();
    InputDataDto inputDataDto;
    OutputDataDto outputDataDto;
    Employee employee;
    Credentials credentials;

    @BeforeEach
    void setUp() throws SQLException {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "employees");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "credentials");
    }

    @Test
    void getDataWhenTypeEqualsOne() {
        inputDataDto = new InputDataDto(null,(byte)1,"Админ","Иванов Иван Иванович", "Менеджер");

        employee = new Employee(inputDataDto.getId(),
                inputDataDto.getRole(),
                inputDataDto.getFio(),
                inputDataDto.getPost(),
                Status.ACTIVE);
        employee = employeeRepository.save(employee);

        credentials = new Credentials(employee.getId(),
                employee.getFio().replaceAll("\\s+", ""),
                employee.getFio().concat(String.valueOf(employee.getId())),
                employee);
        credentialsRepository.save(credentials);

        int id = 1;
        String role = "Админ";
        String fio = "Иванов Иван Иванович";
        String post = "Менеджер";

        assertEquals(1, JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,"Employees","id = '" + id + "'"));
        assertEquals(1, JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,"Employees","role = '" + role + "'"));
        assertEquals(1, JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,"Employees","fio = '" + fio + "'"));
        assertEquals(1, JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,"Employees","post = '" + post + "'"));
        assertThat(employee.getId(), equalTo(credentials.getId()));
        assertThat("ИвановИванИванович", equalTo(credentials.getUserName()));

        //*********************************

        inputDataDto = new InputDataDto(null,(byte)1,"Админ","Петров Петр Петрович", "Менеджер");

        employee = new Employee(inputDataDto.getId(),
                inputDataDto.getRole(),
                inputDataDto.getFio(),
                inputDataDto.getPost(),
                Status.ACTIVE);
        employee = employeeRepository.save(employee);

        credentials = new Credentials(employee.getId(),
                employee.getFio().replaceAll("\\s+", ""),
                employee.getFio().concat(String.valueOf(employee.getId())),
                employee);
        credentialsRepository.save(credentials);

        int id2 = 2;
        String role2 = "Админ";
        String fio2 = "Петров Петр Петрович";
        String post2 = "Менеджер";

        assertEquals(1, JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,"Employees","id = '" + id2 + "'"));
        assertEquals(2, JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,"Employees","role = '" + role2 + "'"));
        assertEquals(1, JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,"Employees","fio = '" + fio2 + "'"));
        assertEquals(2, JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,"Employees","post = '" + post2 + "'"));
        assertThat(employee.getId(), equalTo(credentials.getId()));
        assertThat("ПетровПетрПетрович", equalTo(credentials.getUserName()));

        /*OutputDataDto outputDataDto =
                restTemplate.postForObject("http://localhost:3000/credentials",);*/

    }
}