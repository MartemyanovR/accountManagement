package com.example.accountManagement.event;

import com.example.accountManagement.service.messages.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OutputEventListener {

    private final ProducerService producerService;

    @Autowired
    public OutputEventListener(ProducerService producerService) {
        this.producerService = producerService;
    }

    /**
     * Принимает событие при формировании выходного JSON объекта.
     *И передает его для отправки сообщением в топик Credentials
     * @param event событие формируется в классе ConsumerService
     * в методе getOutputDataDtoFromInputDataDto
     */
    @EventListener
    public void handleOutputEvent(OutputSpringEvent event) {
        producerService.getEventWithOutputJson(event);
    }
}
