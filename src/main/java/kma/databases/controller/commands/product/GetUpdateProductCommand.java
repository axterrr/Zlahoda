package kma.databases.controller.commands.product;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.Employee;
import kma.databases.entities.Product;
import kma.databases.services.CategoryService;
import kma.databases.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class GetUpdateProductCommand implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;

    public GetUpdateProductCommand(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        HttpServletRequest request = httpWrapper.getRequest();
        Long productId = Long.parseLong(request.getParameter(Attribute.ID_PRODUCT));
        Optional<Product> product = productService.getProductById(productId);
        request.setAttribute(Attribute.PRODUCT_DTO, product.get());
        request.setAttribute(Attribute.CATEGORIES, categoryService.getAllCategories());
        return Page.ADD_UPDATE_PRODUCT_VIEW;
    }
}
