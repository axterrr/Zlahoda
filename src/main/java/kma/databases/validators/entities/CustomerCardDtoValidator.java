package kma.databases.validators.entities;

import kma.databases.dto.CustomerCardDto;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class CustomerCardDtoValidator implements Validator<CustomerCardDto> {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static class Holder {
        static final CustomerCardDtoValidator INSTANCE = new CustomerCardDtoValidator();
    }

    public static CustomerCardDtoValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(CustomerCardDto customerCardDto) {
        List<String> errors = new ArrayList<>();
        fieldValidator.validateField(FieldValidatorKey.NAME_SURNAME, customerCardDto.getSurname(), errors);
        fieldValidator.validateField(FieldValidatorKey.NAME_SURNAME, customerCardDto.getName(), errors);
        fieldValidator.validateField(FieldValidatorKey.PATRONYMIC, customerCardDto.getPatronymic(), errors);
        fieldValidator.validateField(FieldValidatorKey.PHONE, customerCardDto.getPhoneNumber(), errors);
        fieldValidator.validateField(FieldValidatorKey.OPTIONAL_CITY, customerCardDto.getCity(), errors);
        fieldValidator.validateField(FieldValidatorKey.OPTIONAL_STREET, customerCardDto.getStreet(), errors);
        fieldValidator.validateField(FieldValidatorKey.OPTIONAL_ZIP_CODE, customerCardDto.getZipCode(), errors);
        fieldValidator.validateField(FieldValidatorKey.PERCENT, customerCardDto.getPercent(), errors);
        return errors;
    }
}
