package kma.databases.validators.entities;

import kma.databases.dto.SaleDto;
import kma.databases.entities.Sale;
import kma.databases.services.StoreProductService;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class SaleDtoValidator implements Validator<Sale> {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static class Holder {
        static final SaleDtoValidator INSTANCE = new SaleDtoValidator();
    }

    public static SaleDtoValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(Sale saleDto) {
        List<String> errors = new ArrayList<>();
        fieldValidator.validateField(FieldValidatorKey.NUMBER, String.valueOf(saleDto.getProductsNumber()), errors);
        fieldValidator.validateField(FieldValidatorKey.CURRENCY, String.valueOf(saleDto.getPrice()), errors);
        Long maxAmount = StoreProductService.getInstance().getStoreProductById(saleDto.getStoreProduct().getUpc()).get().getAmount();
        if(saleDto.getProductsNumber() > maxAmount)
            errors.add("Not enough products in the store");
        return errors;
    }
}
