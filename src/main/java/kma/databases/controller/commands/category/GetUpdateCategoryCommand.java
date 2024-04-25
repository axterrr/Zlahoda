package kma.databases.controller.commands.category;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.Category;
import kma.databases.services.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class GetUpdateCategoryCommand implements Command {

    private final CategoryService categoryService;

    public GetUpdateCategoryCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        HttpServletRequest request = httpWrapper.getRequest();
        Long categoryId = Long.parseLong(request.getParameter(Attribute.ID_CATEGORY));
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        request.setAttribute(Attribute.CATEGORY_DTO, category.get());
        return Page.ADD_UPDATE_CATEGORY_VIEW;
    }
}
