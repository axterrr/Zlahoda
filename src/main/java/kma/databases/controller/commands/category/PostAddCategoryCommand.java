package kma.databases.controller.commands.category;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.dto.CategoryDto;
import kma.databases.entities.Category;
import kma.databases.services.CategoryService;
import kma.databases.validators.entities.CategoryDtoValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostAddCategoryCommand implements Command {

    private final CategoryService categoryService;

    public PostAddCategoryCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        CategoryDto categoryDto = new CategoryDto.Builder()
                .setName(httpWrapper.getRequest().getParameter(Attribute.NAME))
                .build();
        List<String> errors = CategoryDtoValidator.getInstance().validate(categoryDto);

        if (errors.isEmpty()) {
            categoryService.createCategory(categoryDto);
            redirectToAllCategoriesPageWithSuccessMessage(httpWrapper);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(httpWrapper.getRequest(), categoryDto, errors);
        return Page.ADD_UPDATE_CATEGORY_VIEW;
    }

    private void redirectToAllCategoriesPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Category successfully added");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CATEGORIES, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, CategoryDto categoryDto, List<String> errors) {
        request.setAttribute(Attribute.CATEGORY_DTO, categoryDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
