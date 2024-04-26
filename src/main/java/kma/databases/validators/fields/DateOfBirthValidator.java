package kma.databases.validators.fields;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//not null 18 years
public class DateOfBirthValidator extends AbstractFieldValidatorHandler{

    DateOfBirthValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final DateOfBirthValidator INSTANCE = new DateOfBirthValidator(FieldValidatorKey.DATE_OF_BIRTH);
    }

    public static DateOfBirthValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty()) {
            errors.add("Invalid date value");
            return;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(fieldValue);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -18);
            Date eighteenYearsAgo = calendar.getTime();
            if (date.after(eighteenYearsAgo)) {
                errors.add("Employee must be more than 18 years old");
            }
        } catch (ParseException e) {
            errors.add("Invalid date value");
        }
    }
}
