package kma.databases.controller.commands.category;

import kma.databases.constants.Attribute;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.services.CategoryService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteCategoryCommand implements Command {

    private final CategoryService categoryService;

    public DeleteCategoryCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        Long categoryId = Long.parseLong(httpWrapper.getRequest().getParameter(Attribute.ID_CATEGORY));
        categoryService.deleteCategory(categoryId);
        redirectToAllCategoriesPageWithSuccessMessage(httpWrapper);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllCategoriesPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Category successfully deleted");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CATEGORIES, urlParams);
    }
}
