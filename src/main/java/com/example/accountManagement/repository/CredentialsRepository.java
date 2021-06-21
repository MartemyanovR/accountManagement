package com.example.accountManagement.repository;

import com.example.accountManagement.model.Credentials;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Интерфейс предаставляющий методы для взаимодействия
 * с таблицей credentials
 */
@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Integer> {

    /**
     * Метод обновляет все строки таблицы credentials
     * @param userName
     * @param password
     * @param id
     * @return количество обновленных строк в БД
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE Credentials c SET c.userName = :userName," +
            "c.password = :password WHERE c.id = :id")
    int updateCredentials(@Param("userName") String userName, @Param("password") String password,
                          @Param("id") Integer id);

}
