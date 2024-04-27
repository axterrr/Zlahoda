package kma.databases.services;

import kma.databases.converters.EmployeeDtoEmployeeConverter;
import kma.databases.dao.DaoFactory;
import kma.databases.dao.EmployeeDao;
import kma.databases.dto.CredentialsDto;
import kma.databases.dto.EmployeeDto;
import kma.databases.entities.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final DaoFactory daoFactory;

    EmployeeService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final EmployeeService INSTANCE = new EmployeeService(DaoFactory.getDaoFactory());
    }

    public static EmployeeService getInstance() {
        return EmployeeService.Holder.INSTANCE;
    }

    public List<Employee> getAllEmployees() {
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getAll();
        }
    }

    public Optional<Employee> getEmployeeById(String employeeID) {
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getById(employeeID);
        }
    }

    public Optional<Employee> getEmployeeByCredentials(CredentialsDto credentials) {
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getByCredentials(credentials.getPhone(), credentials.getPassword());
        }
    }

    public void createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeDtoEmployeeConverter.toEmployee(employeeDto);
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            employeeDao.create(employee);
        }
    }

    public void updateEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeDtoEmployeeConverter.toEmployee(employeeDto);
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            employeeDao.update(employee);
        }
    }

    public void deleteEmployee(String employeeID) {
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            employeeDao.delete(employeeID);
        }
    }

    public List<Employee> searchEmployeeBySurname(String surname) {
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getBySurname(surname);
        }
    }

    public List<Employee> getAllEmployeesCashiers() {
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getAllCashiers();
        }
    }

    public List<Employee> getAllEmployeesManagers() {
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getAllManagers();
        }
    }

    public List<Employee> getBestCashier() {
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getBestCashier();
        }
    }
}
