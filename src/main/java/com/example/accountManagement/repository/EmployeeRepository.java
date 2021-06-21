package com.example.accountManagement.repository;

import com.example.accountManagement.model.Employee;
import com.example.accountManagement.model.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * Интерфейс предаставляющий методы для взаимодействия
 * с таблицей employee
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    /**
     * Метод обновляет все строки таблицы employee
     * @param role роль
     * @param fio  ФИО
     * @param post должность
     * @param id идентификатор
     * @return количество обновленных строк в БД
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE Employee e SET e.role = :role , e.fio = :fio, e.post = :post " +
            "WHERE e.id = :id")
    int updateEmployee(@Param("role") String role, @Param("fio") String fio,
                                      @Param("post")  String post, @Param("id") Long id);

    /**
     * Метод обновляет статус сотрудника
     * @param status статус
     * @param id идентификатор
     * @return количество обновленных строк в БД
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE Employee e SET e.status = :status WHERE e.id = :id")
    int updateStatusEmployee(@Param("status") Status status, @Param("id") Long id);

}
