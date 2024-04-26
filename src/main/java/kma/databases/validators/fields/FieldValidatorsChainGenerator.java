package kma.databases.validators.fields;

import java.util.List;

public class FieldValidatorsChainGenerator {

    public static AbstractFieldValidatorHandler getFieldValidatorsChain() {
        AbstractFieldValidatorHandler characteristicsValidator = CharacteristicsValidator.getInstance();
        AbstractFieldValidatorHandler cityValidator = CityValidator.getInstance();
        AbstractFieldValidatorHandler currencyValidator = CurrencyValidator.getInstance();
        AbstractFieldValidatorHandler dateOfBirthValidator = DateOfBirthValidator.getInstance();
        AbstractFieldValidatorHandler dateValidator = DateValidator.getInstance();
        AbstractFieldValidatorHandler nameSurnameValidator = NameSurnameValidator.getInstance();
        AbstractFieldValidatorHandler numberValidator = NumberValidator.getInstance();
        AbstractFieldValidatorHandler numericIdValidator = NumericIdValidator.getInstance();
        AbstractFieldValidatorHandler optionalCityValidator = OptionalCityValidator.getInstance();
        AbstractFieldValidatorHandler optionalStreetValidator = OptionalStreetValidator.getInstance();
        AbstractFieldValidatorHandler optionalZipCodeValidator = OptionalZipCodeValidator.getInstance();
        AbstractFieldValidatorHandler passwordValidator = PasswordValidator.getInstance();
        AbstractFieldValidatorHandler patronymicValidator = PatronymicValidator.getInstance();
        AbstractFieldValidatorHandler percentValidator = PercentValidator.getInstance();
        AbstractFieldValidatorHandler phoneValidator = PhoneValidator.getInstance();
        AbstractFieldValidatorHandler productCategoryNameValidator = ProductCategoryNameValidator.getInstance();
        AbstractFieldValidatorHandler promotionalValidator = PromotionalValidator.getInstance();
        AbstractFieldValidatorHandler streetValidator = StreetValidator.getInstance();
        AbstractFieldValidatorHandler stringIdValidator = StringIdValidator.getInstance();
        AbstractFieldValidatorHandler zipCodeValidator = ZipCodeValidator.getInstance();

        characteristicsValidator.setNextFieldValidator(cityValidator);
        cityValidator.setNextFieldValidator(currencyValidator);
        currencyValidator.setNextFieldValidator(dateOfBirthValidator);
        dateOfBirthValidator.setNextFieldValidator(dateValidator);
        dateValidator.setNextFieldValidator(nameSurnameValidator);
        nameSurnameValidator.setNextFieldValidator(numberValidator);
        numberValidator.setNextFieldValidator(numericIdValidator);
        numericIdValidator.setNextFieldValidator(optionalCityValidator);
        optionalCityValidator.setNextFieldValidator(optionalStreetValidator);
        optionalStreetValidator.setNextFieldValidator(optionalZipCodeValidator);
        optionalZipCodeValidator.setNextFieldValidator(passwordValidator);
        passwordValidator.setNextFieldValidator(patronymicValidator);
        patronymicValidator.setNextFieldValidator(percentValidator);
        percentValidator.setNextFieldValidator(phoneValidator);
        phoneValidator.setNextFieldValidator(productCategoryNameValidator);
        productCategoryNameValidator.setNextFieldValidator(promotionalValidator);
        promotionalValidator.setNextFieldValidator(streetValidator);
        streetValidator.setNextFieldValidator(stringIdValidator);
        stringIdValidator.setNextFieldValidator(zipCodeValidator);

        return characteristicsValidator;

    }
}
