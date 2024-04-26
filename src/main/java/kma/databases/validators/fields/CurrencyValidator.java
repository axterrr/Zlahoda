package kma.databases.validators.fields;

import java.util.List;

//numeric >=0
public class CurrencyValidator extends AbstractFieldValidatorHandler{

    private static final String CURRENCY_REGEX = "^\\d*\\.?\\d*$";
    CurrencyValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final CurrencyValidator INSTANCE = new CurrencyValidator(FieldValidatorKey.CURRENCY);
    }

    public static CurrencyValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || !fieldValue.matches(CURRENCY_REGEX)) {
            errors.add("Invalid currency value");
            return;
        }
        try {
            double amount = Double.parseDouble(fieldValue);
            if (amount < 0) {
                errors.add("Currency value must be greater than or equal to zero");
            }
        } catch (NumberFormatException e) {
            errors.add("Invalid currency value");
        }
    }

}
