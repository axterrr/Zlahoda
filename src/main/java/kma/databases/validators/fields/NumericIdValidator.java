package kma.databases.validators.fields;

import java.util.List;

//not null all numbers
public class NumericIdValidator extends AbstractFieldValidatorHandler{

    private static final String ID_REGEX = "^\\d*$";
    NumericIdValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final NumericIdValidator INSTANCE = new NumericIdValidator(FieldValidatorKey.NUMERIC_ID);
    }

    public static NumericIdValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || !fieldValue.matches(ID_REGEX)) {
            errors.add("Invalid numeric ID value");
        }
    }
}
