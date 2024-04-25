package kma.databases.controller.commands.product;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.services.CategoryService;
import kma.databases.services.ProductService;

import javax.servlet.ServletException;
import java.io.IOException;

public class GetAddProductCommand implements Command {

    private final CategoryService categoryService;

    public GetAddProductCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        httpWrapper.getRequest().setAttribute(Attribute.CATEGORIES, categoryService.getAllCategories());
        return Page.ADD_UPDATE_PRODUCT_VIEW;
    }
}
