package kma.databases.validators.fields;

import java.util.List;

//not null 50
public class StreetValidator extends AbstractFieldValidatorHandler{
    private static final String STREET_REGEX = "^[A-Za-z(\\s,)\\d]*$";

    StreetValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final StreetValidator INSTANCE = new StreetValidator(FieldValidatorKey.STREET);
    }

    public static StreetValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 50 || !fieldValue.matches(STREET_REGEX)) {
            errors.add("Invalid street value");
        }
    }
}
