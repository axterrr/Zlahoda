package kma.databases.validators.fields;

import java.util.List;

//not null <50
public class NameSurnameValidator extends AbstractFieldValidatorHandler{

    private static final String NAME_REGEX = "^[A-Za-z]*([\\s-][A-Za-z]*)*$";

    NameSurnameValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final NameSurnameValidator INSTANCE = new NameSurnameValidator(FieldValidatorKey.NAME_SURNAME);
    }

    public static NameSurnameValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 50 || !fieldValue.matches(NAME_REGEX)) {
            errors.add("Invalid name value");
        }
    }
}
