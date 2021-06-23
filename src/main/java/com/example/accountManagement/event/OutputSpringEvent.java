package com.example.accountManagement.event;

import org.springframework.context.ApplicationEvent;

public class OutputSpringEvent extends ApplicationEvent {

    private final String outputJson;

    public OutputSpringEvent(Object source, String outputJson) {
        super(source);
        this.outputJson = outputJson;
    }
    public String getOutputData() {
        return outputJson;
    }
}
