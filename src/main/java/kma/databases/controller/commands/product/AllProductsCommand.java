package kma.databases.controller.commands.product;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.Category;
import kma.databases.entities.Product;
import kma.databases.services.CategoryService;
import kma.databases.services.ProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AllProductsCommand implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;

    public AllProductsCommand(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        httpWrapper.getRequest().setAttribute(Attribute.PRODUCTS, products);
        httpWrapper.getRequest().setAttribute(Attribute.CATEGORIES, categories);
        return Page.ALL_PRODUCTS_VIEW;
    }
}
