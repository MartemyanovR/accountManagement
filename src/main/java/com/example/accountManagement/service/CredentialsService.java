package com.example.accountManagement.service;

import com.example.accountManagement.model.Credentials;

import java.util.List;

public interface CredentialsService {

    Credentials getById(Integer id);

    void save(Credentials credentials);

    List<Credentials> getAll();

    int update(String userName, String password, Integer id);
}
