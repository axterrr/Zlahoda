package kma.databases.validators.fields;

import java.util.List;

//not null 7n
public class StringIdValidator extends AbstractFieldValidatorHandler{
    private static final String ID_REGEX = "^[A-Za-z\\d]*$";

    StringIdValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final StringIdValidator INSTANCE = new StringIdValidator(FieldValidatorKey.STRING_ID);
    }

    public static StringIdValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || !fieldValue.matches(ID_REGEX)) {
            errors.add("Invalid string ID value");
        }
    }
}
