package com.example.accountManagement.service.processing;

import com.example.accountManagement.model.Credentials;
import com.example.accountManagement.model.Employee;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Класс для создания объекта учетных записей
 * из данных конкретного сотрудникка
 */
@Component
public class EmployeeToCredentials {

    /**
     * Метод преобразует данные о сотруднике в учетную запись
     * в виде генерируя логин и пароль
     * @param employee обьект из внешнего сервиса
     * @return объект с учетными данными
     */
    public Credentials fillCredentials(Employee employee) {
        Credentials credentials = new Credentials();
        credentials.setId(employee.getId());
        credentials.setUserName(employee.getFio().replaceAll("\\s+", ""));
        credentials.setPassword(generatePassword());
        credentials.setEmployee(employee);
        return credentials;
    }

    /**
     * метод генерации пароля
     * @return пароль
     */
    private String generatePassword() {
        final int lengthPassword = 8;
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$*^?";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[lengthPassword];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< lengthPassword ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return new String(password);
    }

}
