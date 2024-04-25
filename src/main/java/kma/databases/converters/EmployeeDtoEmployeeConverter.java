package kma.databases.converters;

import kma.databases.dto.EmployeeDto;
import kma.databases.entities.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
                .setDateOfBirth(LocalDateTime.of(LocalDate.parse(employeeDto.getDateOfBirth(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIDNIGHT))
                .setDateOfStart(LocalDateTime.of(LocalDate.parse(employeeDto.getDateOfBirth(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIDNIGHT))
                .setPhoneNumber(employeeDto.getPhoneNumber())
                .setCity(employeeDto.getCity())
                .setStreet(employeeDto.getStreet())
                .setZipCode(employeeDto.getZipCode())
                .setPassword(employeeDto.getPassword());
        if(employeeDto.getId()==null) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
            builder.setId(uuid);
            employeeDto.setId(uuid);
        }
        if(employeeDto.getPatronymic()==null || employeeDto.getPatronymic().isEmpty()) {
            builder.setPatronymic(null);
        }
        return builder.build();
    }
}
