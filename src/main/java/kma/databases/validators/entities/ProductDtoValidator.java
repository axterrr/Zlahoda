package kma.databases.validators.entities;

import kma.databases.dto.EmployeeDto;
import kma.databases.dto.ProductDto;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class ProductDtoValidator implements Validator<ProductDto> {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static class Holder {
        static final ProductDtoValidator INSTANCE = new ProductDtoValidator();
    }

    public static ProductDtoValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        fieldValidator.validateField(FieldValidatorKey.PRODUCT_CATEGORY_NAME, productDto.getName(), errors);
        fieldValidator.validateField(FieldValidatorKey.CHARACTERISTICS, productDto.getCharacteristics(), errors);
        return errors;
    }
}
