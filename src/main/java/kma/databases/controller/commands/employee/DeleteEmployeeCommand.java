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
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DeleteEmployeeCommand implements Command {

    private final EmployeeService employeeService;

    public DeleteEmployeeCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        String employeeId = httpWrapper.getRequest().getParameter(Attribute.ID_EMPLOYEE);
        employeeService.deleteEmployee(employeeId);
        redirectToAllEmployeesPageWithSuccessMessage(httpWrapper);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllEmployeesPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Employee successfully deleted");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_EMPLOYEES, urlParams);
    }
}
