package kma.databases.validators.entities;

import kma.databases.dto.StoreProductDto;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class StoreProductDtoValidator implements Validator<StoreProductDto> {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static class Holder {
        static final StoreProductDtoValidator INSTANCE = new StoreProductDtoValidator();
    }

    public static StoreProductDtoValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(StoreProductDto storeProductDto) {
        List<String> errors = new ArrayList<>();
        fieldValidator.validateField(FieldValidatorKey.CURRENCY, storeProductDto.getPrice(), errors);
        fieldValidator.validateField(FieldValidatorKey.NUMBER, storeProductDto.getAmount(), errors);
        fieldValidator.validateField(FieldValidatorKey.PROMOTIONAL, storeProductDto.isPromotional(), errors);
        return errors;
    }
}
