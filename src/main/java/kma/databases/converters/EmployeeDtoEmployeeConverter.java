package kma.databases.converters;

import kma.databases.dto.EmployeeDto;
import kma.databases.entities.Employee;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class EmployeeDtoEmployeeConverter {

    public static Employee toEmployee(EmployeeDto employeeDto) {
        Employee.Builder builder = new Employee.Builder()
                .setId(employeeDto.getId())
                .setSurname(employeeDto.getSurname())
                .setName(employeeDto.getName())
                .setPatronymic(employeeDto.getPatronymic())
                .setRole(employeeDto.getRole())
                .setSalary(new BigDecimal(employeeDto.getSalary()))
                .setDateOfBirth(LocalDateTime.parse(employeeDto.getDateOfBirth()))
                .setDateOfStart(LocalDateTime.parse(employeeDto.getDateOfStart()))
                .setPhoneNumber(employeeDto.getPhoneNumber())
                .setCity(employeeDto.getCity())
                .setStreet(employeeDto.getStreet())
                .setZipCode(employeeDto.getZipCode())
                .setPassword(employeeDto.getPassword());
        if(employeeDto.getId()==null) {
            builder.setId(UUID.randomUUID().toString());
        }
        if(employeeDto.getPatronymic()==null || employeeDto.getPatronymic().isEmpty()) {
            builder.setPatronymic(null);
        }
        return builder.build();
    }
}
