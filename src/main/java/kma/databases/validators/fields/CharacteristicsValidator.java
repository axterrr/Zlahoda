package kma.databases.validators.fields;

import java.util.List;

//not null <100 chars
public class CharacteristicsValidator extends AbstractFieldValidatorHandler{

    private static final String CHARACTERISTICS_REGEX = "^[A-Za-z0-9,.;:\\-\\s]*$";

    CharacteristicsValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final CharacteristicsValidator INSTANCE = new CharacteristicsValidator(FieldValidatorKey.CHARACTERISTICS);
    }

    public static CharacteristicsValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 100 || !fieldValue.matches(CHARACTERISTICS_REGEX)) {
            errors.add("Invalid characteristics value");
        }
    }
}
