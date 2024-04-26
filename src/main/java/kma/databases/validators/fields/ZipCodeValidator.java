package kma.databases.validators.fields;

import java.util.List;

//not null 9
public class ZipCodeValidator extends AbstractFieldValidatorHandler{

    private static final String ZIP_CODE_REGEX = "^\\d*$";

    ZipCodeValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final ZipCodeValidator INSTANCE = new ZipCodeValidator(FieldValidatorKey.ZIP_CODE);
    }

    public static ZipCodeValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 9 || !fieldValue.matches(ZIP_CODE_REGEX)) {
            errors.add("Invalid zip code value");
        }
    }
}
