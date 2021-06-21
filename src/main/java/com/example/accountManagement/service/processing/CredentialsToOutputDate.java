package com.example.accountManagement.service.processing;

import com.example.accountManagement.model.Credentials;
import com.example.accountManagement.model.dto.OutputDataDto;
import org.springframework.stereotype.Component;

/**
 * Класс преобразования модели в класс DTO
 * для отправки
 */
@Component
public class CredentialsToOutputDate {

    /**
     * Преобразование credentials сущности OutputDataDto объект
     * @param credentials
     * @return готовый объект для отправки данных на внешний сервис
     */
    public OutputDataDto fillOutputDate(Credentials credentials) {
        final OutputDataDto outputDataDto = new OutputDataDto();
        outputDataDto.setId(credentials.getId());
        outputDataDto.setUserName(credentials.getUserName());
        outputDataDto.setPassword(credentials.getPassword());
        return outputDataDto;
    }
}
