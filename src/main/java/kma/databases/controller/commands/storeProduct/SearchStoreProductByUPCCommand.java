package kma.databases.controller.commands.storeProduct;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.entities.Product;
import kma.databases.entities.StoreProduct;
import kma.databases.services.ProductService;
import kma.databases.services.StoreProductService;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchStoreProductByUPCCommand implements Command {

    private final StoreProductService storeProductService;

    public SearchStoreProductByUPCCommand(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        String UPC = httpWrapper.getRequest().getParameter(Attribute.STORE_PRODUCT);
        List<String> errors = validateUserInput(UPC);
        Map<String, String> urlParams;

        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_STORE_PRODUCTS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        List<StoreProduct> products = storeProductService.searchStoreProductsByUPC(UPC);

        if (products.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, "Store product is not found");
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_STORE_PRODUCTS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        httpWrapper.getRequest().setAttribute(Attribute.PRODUCTS, products);
        return Page.ALL_STORE_PRODUCTS_VIEW;
    }

    private List<String> validateUserInput(String id) {
        List<String> errors = new ArrayList<>();
        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.STRING_ID, id, errors);
        return errors;
    }
}
