package kma.databases.validators.fields;

import java.util.List;

//null <50
public class OptionalCityValidator extends AbstractFieldValidatorHandler{

    private static final String CITY_REGEX = "^[A-Za-z]*([\\s-][A-Za-z]*)*$";

    OptionalCityValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final OptionalCityValidator INSTANCE = new OptionalCityValidator(FieldValidatorKey.OPTIONAL_CITY);
    }

    public static OptionalCityValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(!fieldValue.isEmpty() && (fieldValue.length() > 50 || !fieldValue.matches(CITY_REGEX))) {
            errors.add("Invalid city value");
        }
    }
}
