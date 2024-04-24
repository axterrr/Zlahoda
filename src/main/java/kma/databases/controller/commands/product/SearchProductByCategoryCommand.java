package kma.databases.controller.commands.product;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.entities.Product;
import kma.databases.services.ProductService;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchProductByCategoryCommand implements Command {

    private final ProductService productService;

    public SearchProductByCategoryCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        String categoryId = httpWrapper.getRequest().getParameter(Attribute.CATEGORY);
        List<String> errors = validateUserInput(categoryId);
        Map<String, String> urlParams;

        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_PRODUCTS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        List<Product> products = productService.searchProductByCategory(Long.parseLong(categoryId));

        if (products.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, "Product is not found");
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_PRODUCTS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        httpWrapper.getRequest().setAttribute(Attribute.PRODUCTS, products);
        return Page.ALL_PRODUCTS_VIEW;
    }

    private List<String> validateUserInput(String id) {
        List<String> errors = new ArrayList<>();
        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.NUMERIC_ID, id, errors);
        return errors;
    }
}
