package kma.databases.dao;

import kma.databases.entities.Employee;

import java.util.Optional;

public interface EmployeeDao extends GenericDao<Employee, String>, AutoCloseable {

    Optional<Employee> getByCredentials(String phone, String password);

    void close();
}
