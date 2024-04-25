package kma.databases.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee {

    private String id;
    private String surname;
    private String name;
    private String patronymic;
    private Role role;
    private BigDecimal salary;
    private LocalDateTime dateOfBirth;
    private LocalDateTime dateOfStart;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
    private String password;

    public static class Builder implements IBuilder<Employee> {

        Employee employee = new Employee();

        public Builder setId(String id) {
            employee.id = id;
            return this;
        }

        public Builder setSurname(String surname) {
            employee.surname = surname;
            return this;
        }

        public Builder setName(String name) {
            employee.name = name;
            return this;
        }

        public Builder setPatronymic(String patronymic) {
            employee.patronymic = patronymic;
            return this;
        }

        public Builder setRole(Role role) {
            employee.role = role;
            return this;
        }

        public Builder setSalary(BigDecimal salary) {
            employee.salary = salary;
            return this;
        }

        public Builder setDateOfBirth(LocalDateTime dateOfBirth) {
            employee.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setDateOfStart(LocalDateTime dateOfStart) {
            employee.dateOfStart = dateOfStart;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            employee.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setCity(String city) {
            employee.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            employee.street = street;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            employee.zipCode = zipCode;
            return this;
        }

        public Builder setPassword(String password) {
            employee.password = password;
            return this;
        }

        @Override
        public Employee build() {
            return employee;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirthString() {
        return dateOfStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDateTime getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDateTime dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public String getDateOfStartString() {
        return dateOfStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
