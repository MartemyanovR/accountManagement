package com.example.accountManagement.converter;


import com.example.accountManagement.model.dto.InputDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Класс для получения внешних данных из JSON-обьекта
 */
@Slf4j
@Component
public class JsonToInputDataConverter {


    private static final String URL_INPUT_DATA = "http://localhost:3000/employees";

    /**
     * Метод запрашивает данные с вненего сервиса, и сохраняет
     * в класс InputDataDto
     * @return DTO класс
     */
    public InputDataDto getInputData() {
        RestTemplate restTemplate = new RestTemplate();
        InputDataDto inputDataDto = restTemplate.getForObject(URL_INPUT_DATA, InputDataDto.class);
        log.info("Getting data from JSON: {}" , inputDataDto);
        return inputDataDto;
    }

}
