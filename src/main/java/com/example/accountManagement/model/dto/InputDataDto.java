package com.example.accountManagement.model.dto;

import lombok.*;

/**
 * Класс для передачи входных данных с web-сервиса
 * в сущность employee
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputDataDto {

    private Integer id;
    private Byte type;
    private String role;
    private String fio;
    private String post;

    public String toString() {
        return String.format("\n id: %d \n type: %d \n role: %s" +
                " \n fio: %s \n post: %S", id, type, role, fio, post);
    }


}
