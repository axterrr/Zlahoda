package kma.databases.controller.commands.check;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.services.CheckService;
import kma.databases.services.EmployeeService;
import kma.databases.services.ProductService;

import javax.servlet.ServletException;
import java.io.IOException;

public class FindPromotionalChecksCommand implements Command {

    private final CheckService checkService;

    public FindPromotionalChecksCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        httpWrapper.getRequest().setAttribute(Attribute.CHECKS, checkService.getPromotionalChecks());
        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, EmployeeService.getInstance().getAllEmployeesCashiers());
        httpWrapper.getRequest().setAttribute(Attribute.PRODUCTS, ProductService.getInstance().getAllProducts());
        return Page.ALL_CHECKS_VIEW;
    }
}
