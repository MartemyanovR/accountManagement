package com.example.accountManagement.repository;

import com.example.accountManagement.model.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Integer> {


}
