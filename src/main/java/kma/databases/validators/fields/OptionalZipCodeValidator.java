package kma.databases.validators.fields;

import java.util.List;

//null 9
public class OptionalZipCodeValidator extends AbstractFieldValidatorHandler{

    private static final String ZIP_CODE_REGEX = "^\\d*$";

    OptionalZipCodeValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final OptionalZipCodeValidator INSTANCE = new OptionalZipCodeValidator(FieldValidatorKey.OPTIONAL_ZIP_CODE);
    }

    public static OptionalZipCodeValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(!fieldValue.isEmpty() && (fieldValue.length() > 9 || !fieldValue.matches(ZIP_CODE_REGEX))) {
            errors.add("Invalid zip code value");
        }
    }
}
