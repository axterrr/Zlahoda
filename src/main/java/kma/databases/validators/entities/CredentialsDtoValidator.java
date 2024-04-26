package kma.databases.validators.entities;

import kma.databases.dto.CredentialsDto;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class CredentialsDtoValidator implements Validator<CredentialsDto> {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static class Holder {
        static final CredentialsDtoValidator INSTANCE = new CredentialsDtoValidator();
    }

    public static CredentialsDtoValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(CredentialsDto credentialsDto) {
        List<String> errors = new ArrayList<>();
        fieldValidator.validateField(FieldValidatorKey.PHONE, credentialsDto.getPhone(), errors);
        fieldValidator.validateField(FieldValidatorKey.HASH_PASSWORD, credentialsDto.getPassword(), errors);
        return errors;
    }
}
