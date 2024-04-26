package kma.databases.controller.commands.check;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.SessionManager;
import kma.databases.entities.Check;
import kma.databases.entities.Employee;
import kma.databases.entities.Role;
import kma.databases.services.CheckService;
import kma.databases.services.EmployeeService;
import kma.databases.services.ProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AllChecksCommand implements Command {

    private final CheckService checkService;

    public AllChecksCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        Employee loggedInUser = SessionManager.getInstance().getUserFromSession(httpWrapper.getRequest().getSession());
        List<Check> checks;

        checks = checkService.getAllChecks();

        /*if (loggedInUser.getRole().equals(Role.MANAGER)) {
            checks = checkService.getAllChecks();
        } else if (loggedInUser.getRole().equals(Role.CASHIER)) {
            checks = checkService.getChecksByCashierAndDate(loggedInUser.getId(), LocalDate.now(), LocalDate.now().plusDays(1));
        }*/

        httpWrapper.getRequest().setAttribute(Attribute.CHECKS, checks);
        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, EmployeeService.getInstance().getAllEmployeesCashiers());
        httpWrapper.getRequest().setAttribute(Attribute.PRODUCTS, ProductService.getInstance().getAllProducts());
        return Page.ALL_CHECKS_VIEW;
    }
}
