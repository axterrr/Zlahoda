package kma.databases.validators.entities;

import kma.databases.dto.CheckDto;
import kma.databases.entities.Sale;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class CheckDtoValidator implements Validator<CheckDto> {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static class Holder {
        static final CheckDtoValidator INSTANCE = new CheckDtoValidator();
    }

    public static CheckDtoValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(CheckDto checkDto) {
        List<String> errors = new ArrayList<>();
        fieldValidator.validateField(FieldValidatorKey.CURRENCY, checkDto.getTotalSum(), errors);
        fieldValidator.validateField(FieldValidatorKey.CURRENCY, checkDto.getVat(), errors);
        for(Sale s : checkDto.getSales())
            errors.addAll(SaleDtoValidator.getInstance().validate(s));
        return errors;
    }
}
