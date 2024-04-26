package kma.databases.validators.fields;

import java.util.List;

//not null 0
public class PercentValidator extends AbstractFieldValidatorHandler{

    private static final String PERCENT_REGEX = "^\\d*$";
    PercentValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PercentValidator INSTANCE = new PercentValidator(FieldValidatorKey.PERCENT);
    }

    public static PercentValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || !fieldValue.matches(PERCENT_REGEX)) {
            errors.add("Invalid percent value");
            return;
        }
        try {
            int amount = Integer.parseInt(fieldValue);
            if (amount < 0 || amount > 100) {
                errors.add("Percent value must be between 0 and 100");
            }
        } catch (NumberFormatException e) {
            errors.add("Invalid percent value");
        }
    }
}
