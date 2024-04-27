package kma.databases.dao.jdbc;

import kma.databases.dao.EmployeeDao;
import kma.databases.entities.Category;
import kma.databases.entities.Employee;
import kma.databases.entities.Role;
import kma.databases.exceptions.ServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcEmployeeDao implements EmployeeDao {

    private static String GET_ALL = "SELECT * FROM `employee` ORDER BY empl_surname";
    private static String GET_BY_ID = "SELECT * FROM `employee` WHERE id_employee=?";
    private static String GET_BY_CREDENTIALS = "SELECT * FROM `employee` WHERE phone_number=? AND password=?";
    private static String CREATE = "INSERT INTO `employee` " +
            "(id_employee, empl_surname, empl_name, empl_patronymic, empl_role, salary, date_of_birth, date_of_start, " +
            "phone_number, city, street, zip_code, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String UPDATE = "UPDATE `employee` SET empl_surname=?, empl_name=?, empl_patronymic=?, empl_role=?, " +
            "salary=?, date_of_birth=?, phone_number=?, city=?, street=?, zip_code=?, password=? WHERE id_employee=?";
    private static String DELETE = "DELETE FROM `employee` WHERE id_employee=?";
    private static String GET_ALL_CASHIERS = "SELECT * FROM `employee` WHERE empl_role='cashier' ORDER BY empl_surname";
    private static String GET_ALL_MANAGERS = "SELECT * FROM `employee` WHERE empl_role='manager' ORDER BY empl_surname";
    private static String GET_BY_SURNAME = "SELECT * FROM `employee` WHERE LOWER(empl_surname) LIKE CONCAT('%', LOWER(?), '%')";
    private static String GET_BEST_WORKER =
            "SELECT id_employee, empl_surname, empl_name, empl_patronymic, empl_role, salary, date_of_birth, date_of_start, phone_number, city, street, zip_code, password " +
            "FROM employee JOIN `check` USING (id_employee) " +
            "JOIN `sale` USING (check_number) " +
            "GROUP BY id_employee, empl_surname, empl_name, empl_patronymic, empl_role, salary, date_of_birth, date_of_start, phone_number, city, street, zip_code, password " +
            "HAVING SUM(product_number) >= (SELECT MAX(sum)" +
                    "                       FROM (SELECT SUM(product_number) AS sum" +
                    "                             FROM employee JOIN `check` USING (id_employee)" +
                    "                             JOIN sale USING (check_number)" +
                    "                             GROUP BY id_employee) as max) ";

    private static String ID = "id_employee";
    private static String SURNAME = "empl_surname";
    private static String NAME = "empl_name";
    private static String PARTONYMIC = "empl_patronymic";
    private static String ROLE = "empl_role";
    private static String SALARY = "salary";
    private static String DATE_OF_BIRTH = "date_of_birth";
    private static String DATE_OF_START = "date_of_start";
    private static String PHONE_NUMBER = "employee.phone_number";
    private static String CITY = "employee.city";
    private static String STREET = "employee.street";
    private static String ZIP_CODE = "employee.zip_code";
    private static String PASSWORD = "password";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcEmployeeDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcEmployeeDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                employees.add(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return employees;
    }

    @Override
    public Optional<Employee> getById(String id) {
        Optional<Employee> employee = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                employee = Optional.of(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return employee;
    }

    @Override
    public Optional<Employee> getByCredentials(String phone, String password) {
        Optional<Employee> employee = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_CREDENTIALS)) {
            query.setString(1, phone);
            query.setString(2, password);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                employee = Optional.of(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return employee;
    }

    @Override
    public void create(Employee employee) {
        try (PreparedStatement query = connection.prepareStatement(CREATE)) {
            query.setString(1, employee.getId());
            query.setString(2, employee.getSurname());
            query.setString(3, employee.getName());
            query.setString(4, employee.getPatronymic());
            query.setString(5, employee.getRole().getValue());
            query.setBigDecimal(6, employee.getSalary());
            query.setTimestamp(7, Timestamp.valueOf(employee.getDateOfBirth()));
            query.setTimestamp(8, Timestamp.valueOf(employee.getDateOfStart()));
            query.setString(9, employee.getPhoneNumber());
            query.setString(10, employee.getCity());
            query.setString(11, employee.getStreet());
            query.setString(12, employee.getZipCode());
            query.setString(13, employee.getPassword());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Employee employee) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, employee.getSurname());
            query.setString(2, employee.getName());
            query.setString(3, employee.getPatronymic());
            query.setString(4, employee.getRole().getValue());
            query.setBigDecimal(5, employee.getSalary());
            query.setTimestamp(6, Timestamp.valueOf(employee.getDateOfBirth()));
            query.setString(7, employee.getPhoneNumber());
            query.setString(8, employee.getCity());
            query.setString(9, employee.getStreet());
            query.setString(10, employee.getZipCode());
            query.setString(11, employee.getPassword());
            query.setString(12, employee.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public List<Employee> getAllCashiers() {
        List<Employee> employees = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL_CASHIERS)) {
            while (resultSet.next()) {
                employees.add(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return employees;
    }

    @Override
    public List<Employee> getAllManagers() {
        List<Employee> employees = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL_MANAGERS)) {
            while (resultSet.next()) {
                employees.add(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return employees;
    }

    @Override
    public List<Employee> getBySurname(String surname) {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_SURNAME)) {
            query.setString(1, surname);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                employees.add(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return employees;
    }

    @Override
    public List<Employee> getBestCashier() {
        List<Employee> employee = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BEST_WORKER)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                employee.add(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return employee;
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ServerException(e);
            }
        }
    }

    protected static Employee extractEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        return new Employee.Builder()
                .setId(resultSet.getString(ID))
                .setSurname(resultSet.getString(SURNAME))
                .setName(resultSet.getString(NAME))
                .setPatronymic(resultSet.getString(PARTONYMIC))
                .setRole(Role.getRole(resultSet.getString(ROLE)))
                .setSalary(resultSet.getBigDecimal(SALARY))
                .setDateOfBirth(resultSet.getTimestamp(DATE_OF_BIRTH).toLocalDateTime())
                .setDateOfStart(resultSet.getTimestamp(DATE_OF_START).toLocalDateTime())
                .setPhoneNumber(resultSet.getString(PHONE_NUMBER))
                .setCity(resultSet.getString(CITY))
                .setStreet(resultSet.getString(STREET))
                .setZipCode(resultSet.getString(ZIP_CODE))
                .setPassword(resultSet.getString(PASSWORD)).build();
    }

}
