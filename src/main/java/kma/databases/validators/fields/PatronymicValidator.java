package kma.databases.validators.fields;

import java.util.List;

//null 50
public class PatronymicValidator extends AbstractFieldValidatorHandler{
    private static final String NAME_REGEX = "^[A-Za-z]*([\\s-][A-Za-z]*)*$";

    PatronymicValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PatronymicValidator INSTANCE = new PatronymicValidator(FieldValidatorKey.PATRONYMIC);
    }

    public static PatronymicValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(!fieldValue.isEmpty() && (fieldValue.length() > 50 || !fieldValue.matches(NAME_REGEX))) {
            errors.add("Invalid patronymic value");
        }
    }
}
