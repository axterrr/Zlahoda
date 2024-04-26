package kma.databases.validators.fields;

import java.util.List;

//null <50
public class OptionalStreetValidator extends  AbstractFieldValidatorHandler{

    private static final String STREET_REGEX = "^[A-Za-z(\\s,)\\d]*$";

    OptionalStreetValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final OptionalStreetValidator INSTANCE = new OptionalStreetValidator(FieldValidatorKey.OPTIONAL_STREET);
    }

    public static OptionalStreetValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(!fieldValue.isEmpty() && (fieldValue.length() > 50 || !fieldValue.matches(STREET_REGEX))) {
            errors.add("Invalid street value");
        }
    }
}
