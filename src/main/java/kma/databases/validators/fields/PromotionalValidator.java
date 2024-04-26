package kma.databases.validators.fields;

import java.util.List;

//not null bool
public class PromotionalValidator extends AbstractFieldValidatorHandler{

    PromotionalValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PromotionalValidator INSTANCE = new PromotionalValidator(FieldValidatorKey.PROMOTIONAL);
    }

    public static PromotionalValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(!fieldValue.equals("false") && !fieldValue.equals("true")) {
            errors.add("Invalid Is promotional value");
        }
    }

}
