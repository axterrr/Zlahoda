package kma.databases.controller.commands.employee;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.SessionManager;
import kma.databases.entities.CustomerCard;
import kma.databases.entities.Employee;
import kma.databases.entities.Role;
import kma.databases.services.CustomerCardService;
import kma.databases.services.EmployeeService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AllEmployeesCommand implements Command {

    private final EmployeeService employeeService;

    public AllEmployeesCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        Employee loggedInUser = SessionManager.getInstance().getUserFromSession(httpWrapper.getRequest().getSession());
        List<Employee> empls = new ArrayList<>();

        if (loggedInUser.getRole().equals(Role.MANAGER)) {
            empls = employeeService.getAllEmployees();
        } else if (loggedInUser.getRole().equals(Role.CASHIER)) {
            Optional<Employee> cashier = employeeService.getEmployeeById(loggedInUser.getId());
            if(cashier.isPresent())
                empls.add(cashier.get());
        }

        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, empls);
        return Page.ALL_EMPLOYEES_VIEW;
    }
}
