package com.example.accountManagement.controller;

import com.example.accountManagement.model.dto.InputDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("exchange")
public class AccountRestController {

    private final KafkaTemplate<String, InputDataDto> kafkaTemplateInputData;

    @Autowired
    public AccountRestController(KafkaTemplate<String, InputDataDto> kafkaTemplateInputData) {
        this.kafkaTemplateInputData = kafkaTemplateInputData;
    }

    @Value("${INPUT_TOPIC}")
    private  String inputTopic;


    /**
     * Метод для публикации входных данных.
     * Пример POST запроса:
     * {
     *     "id": null,
     *     "type": 2,
     *     "role": "User",
     *     "fio": "Иванов Иван Иванович",
     *     "post": "Инженер"
     * }
     * @param inputDataJson входной JSON объект
     * @throws Exception при получении данных
     */
    @PostMapping("/input")
    public void getInputData(@RequestBody InputDataDto inputDataJson) throws Exception{
        try {
            ListenableFuture<SendResult<String, InputDataDto>> future =
                    kafkaTemplateInputData.send(inputTopic, inputDataJson);
            future.addCallback(System.out::println, System.err::println);
            kafkaTemplateInputData.flush();
            log.info("Incoming object from topic subscription Employee: {}", inputDataJson);
        } catch(Exception e) {
            log.error("An error occurred while receiving data: {}", e.getMessage());
        }
    }
}
