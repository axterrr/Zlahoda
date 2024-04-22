package kma.databases.validators.entities;

import kma.databases.dto.CategoryDto;
import kma.databases.validators.fields.*;

import java.util.ArrayList;
import java.util.List;

public class CategoryDtoValidator implements Validator<CategoryDto> {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static class Holder {
        static final CategoryDtoValidator INSTANCE = new CategoryDtoValidator();
    }

    public static CategoryDtoValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();
        fieldValidator.validateField(FieldValidatorKey.PRODUCT_CATEGORY_NAME, categoryDto.getName(), errors);
        return errors;
    }
}
