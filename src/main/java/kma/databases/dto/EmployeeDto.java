package kma.databases.dto;

import kma.databases.entities.IBuilder;
import kma.databases.entities.Role;

public class EmployeeDto {

    private String id;
    private String surname;
    private String name;
    private String patronymic;
    private Role role;
    private String salary;
    private String dateOfBirth;
    private String dateOfStart;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
    private String password;
    private String confirmPassword;

    public static class Builder implements IBuilder<EmployeeDto> {

        EmployeeDto employee = new EmployeeDto();

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

        public Builder setSalary(String salary) {
            employee.salary = salary;
            return this;
        }

        public Builder setDateOfBirth(String dateOfBirth) {
            employee.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setDateOfStart(String dateOfStart) {
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

        public Builder setConfirmPassword(String confirmPassword) {
            employee.confirmPassword = confirmPassword;
            return this;
        }

        @Override
        public EmployeeDto build() {
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(String dateOfStart) {
        this.dateOfStart = dateOfStart;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
