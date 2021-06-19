package com.example.accountManagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutputDataDto {

    private Integer id;
    private String userName;
    private String password;

    public String toString() {
        return String.format("\n id: %d \n userName: %d \n password: %s"
                , id, userName, password);
    }


}
