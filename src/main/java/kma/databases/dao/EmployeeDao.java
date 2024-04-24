package kma.databases.dao;

import kma.databases.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao extends GenericDao<Employee, String>, AutoCloseable {

    Optional<Employee> getByCredentials(String phone, String password);

    List<Employee> getAllCashiers();

    List<Employee> getBySurname(String surname);

    void close();
}
