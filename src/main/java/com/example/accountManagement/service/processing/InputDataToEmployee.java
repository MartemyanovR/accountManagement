package com.example.accountManagement.service.processing;

import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.Status;
import com.example.accountManagement.model.dto.InputDataDto;
import org.springframework.stereotype.Component;

/**
 * Класс передает данные из внешнего сервиса в объект-сущность
 * Employee для сохранения в БД
 */
@Component
public class InputDataToEmployee {

    /**
     * Заполняет необходимые поля объекта Employee для сохранения в БД
     * @param inputDataDto входные данные
     * @return заполненый объект
     */
    public Employee fillEmployeeForCreate(InputDataDto inputDataDto) {
        Employee employee = new Employee();
        employee.setRole(inputDataDto.getRole());
        employee.setFio(inputDataDto.getFio());
        employee.setPost(inputDataDto.getPost());
        employee.setStatus(Status.ACTIVE);

        return employee;
    }

    /**
     * Заполняет необходимые поля объекта Employee для обновления в БД
     * @param inputDataDto входные данные
     * @return заполненый объект
     */
    public Employee fillEmployeeForUpdate(InputDataDto inputDataDto) {
        Employee employee = new Employee();
        employee.setId(inputDataDto.getId());
        employee.setRole(inputDataDto.getRole());
        employee.setFio(inputDataDto.getFio());
        employee.setPost(inputDataDto.getPost());

        return employee;
    }

    /**
     * Метод возвращает сотрудника с данными необходимыми для
     * обновления статуса в БД
     * @param inputDataDto входные данные
     * @return  объект с информацией о его статусе в БД
     */
    public Employee fillEmployeeForUpdateStatus(InputDataDto inputDataDto) {
        Employee employee = new Employee();
        employee.setId(inputDataDto.getId());

        return employee;
    }

 }
