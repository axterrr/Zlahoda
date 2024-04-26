package kma.databases.validators.fields;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

public class DateValidator extends AbstractFieldValidatorHandler{

    DateValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final DateValidator INSTANCE = new DateValidator(FieldValidatorKey.DATE);
    }

    public static DateValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty()) {
            errors.add("Invalid date value");
            return;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = dateFormat.parse(fieldValue);
            Date today = new Date();
            if (date.after(today)) {
                errors.add("Date can't be in the future");
            }
        } catch (ParseException e) {
            errors.add("Invalid date value");
        }
    }

}
