package kma.databases.controller.commands.employee;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.CustomerCard;
import kma.databases.entities.Employee;
import kma.databases.services.CustomerCardService;
import kma.databases.services.EmployeeService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AllEmployeesCommand implements Command {

    private final EmployeeService employeeService;

    public AllEmployeesCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        List<Employee> employees = employeeService.getAllEmployees();
        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, employees);
        return Page.ALL_EMPLOYEES_VIEW;
    }
}
