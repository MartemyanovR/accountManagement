package com.example.accountManagement.service.processing;

import com.example.accountManagement.model.Credentials;
import com.example.accountManagement.model.dto.OutputDataDto;
import org.springframework.stereotype.Component;

@Component
public class CredentialsToOutputDate {

    public OutputDataDto fillOutputDate(Credentials credentials) {
        final OutputDataDto outputDataDto = new OutputDataDto();
        outputDataDto.setId(credentials.getId());
        outputDataDto.setUserName(credentials.getUserName());
        outputDataDto.setPassword(credentials.getPassword());
        return outputDataDto;
    }
}
