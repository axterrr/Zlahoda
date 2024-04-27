package kma.databases.controller.commands.employee;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.services.CategoryService;
import kma.databases.services.EmployeeService;

import javax.servlet.ServletException;
import java.io.IOException;

public class GetBestCashierCommand implements Command {

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, EmployeeService.getInstance().getBestCashier());
        httpWrapper.getRequest().setAttribute(Attribute.CATEGORIES, CategoryService.getInstance().getAllCategories());
        return Page.ALL_EMPLOYEES_VIEW;
    }
}
