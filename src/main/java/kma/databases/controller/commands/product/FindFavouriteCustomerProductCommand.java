package kma.databases.controller.commands.product;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.entities.Category;
import kma.databases.entities.Product;
import kma.databases.services.CategoryService;
import kma.databases.services.CustomerCardService;
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

public class FindFavouriteCustomerProductCommand implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;

    public FindFavouriteCustomerProductCommand(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        String cardId = httpWrapper.getRequest().getParameter(Attribute.CATEGORY);
        List<String> errors = validateUserInput(cardId);
        Map<String, String> urlParams;

        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_PRODUCTS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        List<Product> products = productService.findCustomerFavouriteProducts(cardId);

        if (products.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, "Product is not found");
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_PRODUCTS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        List<Category> categories = categoryService.getAllCategories();
        httpWrapper.getRequest().setAttribute(Attribute.CUSTOMER_CARDS, CustomerCardService.getInstance().getAllCustomerCards());
        httpWrapper.getRequest().setAttribute(Attribute.CATEGORIES, categories);
        httpWrapper.getRequest().setAttribute(Attribute.PRODUCTS, products);
        return Page.ALL_PRODUCTS_VIEW;
    }

    private List<String> validateUserInput(String id) {
        List<String> errors = new ArrayList<>();
        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.STRING_ID, id, errors);
        return errors;
    }
}
