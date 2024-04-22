package kma.databases.validators.entities;

import kma.databases.dto.SaleDto;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class SaleDtoValidator implements Validator<SaleDto> {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static class Holder {
        static final SaleDtoValidator INSTANCE = new SaleDtoValidator();
    }

    public static SaleDtoValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(SaleDto saleDto) {
        List<String> errors = new ArrayList<>();
        fieldValidator.validateField(FieldValidatorKey.NUMBER, saleDto.getProductsNumber(), errors);
        fieldValidator.validateField(FieldValidatorKey.CURRENCY, saleDto.getPrice(), errors);
        return errors;
    }
}
