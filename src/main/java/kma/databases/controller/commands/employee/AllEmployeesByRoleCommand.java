package kma.databases.controller.commands.employee;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.Employee;
import kma.databases.services.CategoryService;
import kma.databases.services.EmployeeService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllEmployeesByRoleCommand implements Command {

    private final EmployeeService employeeService;

    public AllEmployeesByRoleCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        String role = httpWrapper.getRequest().getParameter(Attribute.ROLE);

        List<Employee> employees = new ArrayList<>();

        if(role == null || role.isEmpty())
            employees = employeeService.getAllEmployees();
        else if(role.equals("cashier"))
            employees = employeeService.getAllEmployeesCashiers();
        else if(role.equals("manager"))
            employees = employeeService.getAllEmployeesManagers();

        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, employees);
        httpWrapper.getRequest().setAttribute(Attribute.CATEGORIES, CategoryService.getInstance().getAllCategories());
        return Page.ALL_EMPLOYEES_VIEW;
    }
}
