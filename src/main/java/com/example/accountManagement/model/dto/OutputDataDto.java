package com.example.accountManagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс получает данные с сущности Credentials,
 * и передает их на web-сервис.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutputDataDto {

    private Long id;
    private String userName;
    private String password;

    public String toString() {
        return String.format("\n id: %d \n userName: %s \n password: %s"
                , id, userName, password);
    }


}
