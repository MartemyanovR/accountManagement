package com.example.accountManagement.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OutputEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public OutputEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    /**
     * Публикация события после формирования выходного JSON
     * @param outputJson выходной JSON-объект
     */
    public void publishCustomEvent(final String outputJson) {
        System.out.println("Publishing custom event. ");
        OutputSpringEvent outputSpringEvent =  new OutputSpringEvent(this, outputJson);
        applicationEventPublisher.publishEvent(outputSpringEvent);
    }
}
