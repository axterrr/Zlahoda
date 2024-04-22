package kma.databases.validators.entities;

import kma.databases.dto.EmployeeDto;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDtoValidator implements Validator<EmployeeDto> {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static class Holder {
        static final EmployeeDtoValidator INSTANCE = new EmployeeDtoValidator();
    }

    public static EmployeeDtoValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(EmployeeDto employeeDto) {
        List<String> errors = new ArrayList<>();
        fieldValidator.validateField(FieldValidatorKey.NAME_SURNAME, employeeDto.getSurname(), errors);
        fieldValidator.validateField(FieldValidatorKey.NAME_SURNAME, employeeDto.getName(), errors);
        fieldValidator.validateField(FieldValidatorKey.PATRONYMIC, employeeDto.getPatronymic(), errors);
        fieldValidator.validateField(FieldValidatorKey.CURRENCY, employeeDto.getSalary(), errors);
        fieldValidator.validateField(FieldValidatorKey.DATE_OF_BIRTH, employeeDto.getDateOfBirth(), errors);
        fieldValidator.validateField(FieldValidatorKey.DATE_OF_START, employeeDto.getDateOfStart(), errors);
        fieldValidator.validateField(FieldValidatorKey.PHONE, employeeDto.getPhoneNumber(), errors);
        fieldValidator.validateField(FieldValidatorKey.CITY, employeeDto.getCity(), errors);
        fieldValidator.validateField(FieldValidatorKey.STREET, employeeDto.getStreet(), errors);
        fieldValidator.validateField(FieldValidatorKey.ZIP_CODE, employeeDto.getZipCode(), errors);
        fieldValidator.validateField(FieldValidatorKey.PASSWORD, employeeDto.getPassword(), errors);
        fieldValidator.validateField(FieldValidatorKey.PASSWORD, employeeDto.getConfirmPassword(), errors);
        if (!employeeDto.getPassword().equals(employeeDto.getConfirmPassword())) {
            errors.add("Incorrect confirm password");
        }
        return errors;
    }
}
