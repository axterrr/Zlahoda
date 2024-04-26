package kma.databases.validators.fields;
//not empty <50

import java.util.List;

//not null <50 chars
public class CityValidator extends AbstractFieldValidatorHandler{

    private static final String CITY_REGEX = "^[A-Za-z]*([\\s-][A-Za-z]*)*$";

    CityValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final CityValidator INSTANCE = new CityValidator(FieldValidatorKey.CITY);
    }

    public static CityValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 50 || !fieldValue.matches(CITY_REGEX)) {
            errors.add("Invalid city value");
        }
    }
}
