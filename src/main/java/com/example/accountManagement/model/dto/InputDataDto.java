package com.example.accountManagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class InputDataDto {

    private Integer id;
    private Byte type;
    private String role;
    private String fio;
    private String post;


}
