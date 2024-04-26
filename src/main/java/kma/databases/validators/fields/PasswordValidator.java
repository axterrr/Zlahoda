package kma.databases.validators.fields;

import java.util.List;

//
public class PasswordValidator extends AbstractFieldValidatorHandler{

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,14}$";

    PasswordValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PasswordValidator INSTANCE = new PasswordValidator(FieldValidatorKey.PASSWORD);
    }

    public static PasswordValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || !fieldValue.matches(PASSWORD_REGEX)) {
            errors.add("Invalid password value. Password must be at least 8 symbols and contain letters and digits");
        }
    }

}
