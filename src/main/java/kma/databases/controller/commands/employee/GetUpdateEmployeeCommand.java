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
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class GetUpdateEmployeeCommand implements Command {

    private final EmployeeService employeeService;

    public GetUpdateEmployeeCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        HttpServletRequest request = httpWrapper.getRequest();
        String employeeId = request.getParameter(Attribute.ID_EMPLOYEE);
        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);
        request.setAttribute(Attribute.EMPLOYEE, employee.get());
        return Page.ADD_UPDATE_EMPLOYEE_VIEW;
    }
}
