package kma.databases.controller.commands.product;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.dto.ProductDto;
import kma.databases.entities.Category;
import kma.databases.services.CategoryService;
import kma.databases.services.ProductService;
import kma.databases.validators.entities.ProductDtoValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUpdateProductCommand implements Command {

    private final ProductService productService;

    public PostUpdateProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        ProductDto productDto = getUserInput(httpWrapper.getRequest());
        List<String> errors = ProductDtoValidator.getInstance().validate(productDto);

        if (errors.isEmpty()) {
            productService.updateProduct(productDto);
            redirectToAllProductsPageWithSuccessMessage(httpWrapper);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(httpWrapper.getRequest(), productDto, errors);
        return Page.ADD_UPDATE_PRODUCT_VIEW;
    }

    private ProductDto getUserInput(HttpServletRequest request) {
        return new ProductDto.Builder()
                .setId(Long.parseLong(request.getParameter(Attribute.ID_PRODUCT)))
                .setName(request.getParameter(Attribute.NAME))
                .setCharacteristics(request.getParameter(Attribute.CHARACTERISTICS))
                .setCategory(new Category.Builder().setNumber(Long.parseLong(request.getParameter(Attribute.CATEGORY))).build())
                .build();
    }

    private void redirectToAllProductsPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Product successfully updated");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_PRODUCTS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, ProductDto productDto, List<String> errors) {
        request.setAttribute(Attribute.CATEGORIES, CategoryService.getInstance().getAllCategories());
        request.setAttribute(Attribute.PRODUCT_DTO, productDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
