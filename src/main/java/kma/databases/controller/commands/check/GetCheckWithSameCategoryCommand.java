package kma.databases.controller.commands.check;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.Check;
import kma.databases.entities.Employee;
import kma.databases.services.CategoryService;
import kma.databases.services.CheckService;
import kma.databases.services.EmployeeService;
import kma.databases.services.ProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GetCheckWithSameCategoryCommand implements Command {


    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        String category = httpWrapper.getRequest().getParameter(Attribute.CATEGORY);
        List<Check> checks = CheckService.getInstance().getChecksWithSameCategory(Long.valueOf(category));
        httpWrapper.getRequest().setAttribute(Attribute.CHECKS, checks);
        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, EmployeeService.getInstance().getAllEmployeesCashiers());
        httpWrapper.getRequest().setAttribute(Attribute.PRODUCTS, ProductService.getInstance().getAllProducts());
        httpWrapper.getRequest().setAttribute(Attribute.CATEGORIES, CategoryService.getInstance().getAllCategories());
        return Page.ALL_CHECKS_VIEW;
    }
}
