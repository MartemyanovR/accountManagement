package com.example.accountManagement.service;

import com.example.accountManagement.model.Credentials;
import com.example.accountManagement.repository.CredentialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CredentialsServiceImpl implements CredentialsService {

    private final CredentialsRepository credentialsRepository;

    @Autowired
    public CredentialsServiceImpl(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public Credentials getById(Integer id) {
        log.info("IN CredentialsServiceImpl getById: {}", id);
        return credentialsRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Credentials credentials) {
        log.info("IN CredentialsServiceImpl save: {}", credentials);
        credentialsRepository.save(credentials);
    }

    @Override
    public List<Credentials> getAll() {
        log.info("IN CredentialsServiceImpl getAll");
        return (List<Credentials>)credentialsRepository.findAll();
    }

    @Override
    public int update(String userName, String password, Integer id) {
        log.info("IN CredentialsServiceImpl update: {}, {}, {}", userName, password, id);
        return credentialsRepository.updateCredentials(userName, password, id);
    }
}
