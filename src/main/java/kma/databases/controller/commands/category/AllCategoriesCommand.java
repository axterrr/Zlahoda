package kma.databases.controller.commands.category;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.Category;
import kma.databases.services.CategoryService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AllCategoriesCommand implements Command {

    private final CategoryService categoryService;

    public AllCategoriesCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        httpWrapper.getRequest().setAttribute(Attribute.CATEGORIES, categories);
        return Page.ALL_CATEGORIES_VIEW;
    }
}
