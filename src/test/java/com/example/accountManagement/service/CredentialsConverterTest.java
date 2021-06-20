package com.example.accountManagement.service;

import com.example.accountManagement.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class CredentialsConverterTest {

    private Employee employee;



    @Test
    void generatePassword() {
        int lengthPassword = 8;
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
        System.out.println(password);

        assertThat(8, equalTo(password.length));
        assertThat(true, is(!Character.isLetter(Character.valueOf(password[2]))));
        assertThat(true, is(Character.isDigit(Character.valueOf(password[3]))));
    }
}