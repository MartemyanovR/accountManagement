package com.example.accountManagement.service.messages;

import com.example.accountManagement.event.OutputEventPublisher;
import com.example.accountManagement.model.dto.InputDataDto;
import com.example.accountManagement.model.dto.OutputDataDto;
import com.example.accountManagement.service.EmployeeCredentialsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class ConsumerService {

    private InputDataDto inputDataDto;
    private final EmployeeCredentialsService employeeCredentialsService;
    private final OutputEventPublisher outputEventPublisher;

    @Autowired
    public ConsumerService(EmployeeCredentialsService employeeCredentialsService,
                           OutputEventPublisher outputEventPublisher) {
       this.employeeCredentialsService = employeeCredentialsService;
        this.outputEventPublisher = outputEventPublisher;
    }

    /**
     * Потребитель входящих сообщений из топика Employee
     * @param inputJson входной JSON-объект
     */
    @KafkaListener(topics = "Employee", groupId = "account-topic")
    public void consume(String inputJson) {
        System.out.println(inputJson);
        getInputDataDtoFromJson(inputJson);
    }

    /**
     * Десереализация входящего объекта
     * @param json ответ от сервера
     */
     public void getInputDataDtoFromJson(@NonNull String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            inputDataDto = objectMapper.readValue(json, InputDataDto.class);
            getOutputDataDtoFromInputDataDto();
        } catch (JsonProcessingException e) {
            log.error("Conversion error from inputJson to InputDataDto object: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Получение результата преобразования входного объекта в выходной
     * и публикация для слушателя OutputEventListener
     */
    public void getOutputDataDtoFromInputDataDto() {
        try {
            OutputDataDto outputDataDto =
                    employeeCredentialsService.chooseActionForStaffChanges(inputDataDto).get();
            String outputJson = getJsonFromOutputDataDtoObject(outputDataDto);
            outputEventPublisher.publishCustomEvent(outputJson);
        } catch(NoSuchElementException e) {
            log.info("Employee ID {} blocked", inputDataDto.getId());
        }
    }

    /**
     * Сериализуем объект OutputDataDto в Json
     * @return Json as String
     */
    private String getJsonFromOutputDataDtoObject(OutputDataDto outputDataDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(outputDataDto);
        } catch (JsonProcessingException e) {
            log.error("Conversion error from OutputDataDto to outputJson object: {}", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}