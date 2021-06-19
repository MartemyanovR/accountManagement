package com.example.accountManagement.service;


import com.example.accountManagement.model.dto.InputDataDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InputDataProcessing {

    //private final WebClient webClient = WebClient.create();
    private static final String URL_INPUT_DATA =
            "https://my-json-server.typicode.com/MartemyanovR/accountManagement/input";


    public InputDataDto getInputDataRest() {
        RestTemplate restTemplate = new RestTemplate();
        InputDataDto inputDataDto = restTemplate.getForObject(URL_INPUT_DATA, InputDataDto.class);
        System.out.println ("Rest\n" + inputDataDto);
        return inputDataDto;
    }

   /* @PostConstruct
    public InputDataDto getInputData() {
         InputDataDto inputDataDto = webClient
                .get()
                .uri(URL_INPUT_DATA)
                .retrieve()
                .bodyToMono(InputDataDto.class)
                .block();
         System.out.println("WEBClient \n" + inputDataDto);
        return inputDataDto;
    }*/




}
