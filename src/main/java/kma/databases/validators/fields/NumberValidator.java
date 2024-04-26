package kma.databases.validators.fields;

import java.util.List;

//not null >0
public class NumberValidator extends AbstractFieldValidatorHandler{
    private static final String NUMBER_REGEX = "^\\d*$";
    NumberValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final NumberValidator INSTANCE = new NumberValidator(FieldValidatorKey.NUMBER);
    }

    public static NumberValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || !fieldValue.matches(NUMBER_REGEX)) {
            errors.add("Invalid number value");
            return;
        }
        try {
            int amount = Integer.parseInt(fieldValue);
            if (amount < 0) {
                errors.add("Number value can't be less than zero");
            }
        } catch (NumberFormatException e) {
            errors.add("Invalid number value");
        }
    }
}
