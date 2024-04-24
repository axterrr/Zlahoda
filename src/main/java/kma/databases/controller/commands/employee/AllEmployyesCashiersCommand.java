package kma.databases.controller.commands.employee;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.entities.Employee;
import kma.databases.services.EmployeeService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllEmployyesCashiersCommand implements Command {

    private final EmployeeService employeeService;

    public AllEmployyesCashiersCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        Map<String, String> urlParams;
        List<Employee> cashiers = employeeService.getAllEmployyesCashiers();

        if (cashiers.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, "Employee is not found");
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_EMPLOYEES, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, cashiers);
        return Page.ALL_EMPLOYEES_VIEW;
    }
}
