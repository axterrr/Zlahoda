package kma.databases.validators.fields;

import java.util.List;

//not null 50
public class ProductCategoryNameValidator extends AbstractFieldValidatorHandler{

    private static final String NAME_REGEX = "^[A-Za-z]*([\\s-][A-Za-z]*)*$";

    ProductCategoryNameValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final ProductCategoryNameValidator INSTANCE = new ProductCategoryNameValidator(FieldValidatorKey.PRODUCT_CATEGORY_NAME);
    }

    public static ProductCategoryNameValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 50 || !fieldValue.matches(NAME_REGEX)) {
            errors.add("Invalid name value");
        }
    }
}
