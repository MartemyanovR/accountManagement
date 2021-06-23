package com.example.accountManagement.service.messages;

import com.example.accountManagement.event.OutputSpringEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Service
public class ProducerService {

    private final KafkaTemplate<String, OutputSpringEvent> kafkaTemplateOutputData;

    @Autowired
    public ProducerService(KafkaTemplate<String, OutputSpringEvent> kafkaTemplateOutputData) {
        this.kafkaTemplateOutputData = kafkaTemplateOutputData;
    }

    @Value("${OUTPUT_TOPIC}")
    private  String outputTopic;

    /**
     * Публикует выходной JSON-объект в виде события в топик Credentials
     * @param event с JSON-объектом
     */
    public void sendOutputJson(OutputSpringEvent event) {
        try {
            ListenableFuture<SendResult<String, OutputSpringEvent>> future =
                    kafkaTemplateOutputData.send(outputTopic, event);
            future.addCallback(System.out::println, System.err::println);
            kafkaTemplateOutputData.flush();
            log.info("The output event object that contains the JSon Employee:" +
                                        " {}, published in Credentials topic", event.getOutputData());
        } catch (Exception e) {
            log.error("An error occurred while receiving data: {}", e.toString());
        }
    }

    public void getEventWithOutputJson(OutputSpringEvent outputSpringEvent) {
        sendOutputJson(outputSpringEvent);
    }

}
