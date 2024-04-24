package kma.databases.controller.commands.employee;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.entities.Employee;
import kma.databases.services.EmployeeService;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEmployeeBySurnameCommand implements Command {

    private final EmployeeService employeeService;

    public SearchEmployeeBySurnameCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        String surname = httpWrapper.getRequest().getParameter(Attribute.SURNAME);
        List<String> errors = validateUserInput(surname);
        Map<String, String> urlParams;

        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_EMPLOYEES, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        List<Employee> users = employeeService.searchEmployeeBySurname(surname);

        if (users.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, "Employee is not found");
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_EMPLOYEES, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, users);
        return Page.ALL_EMPLOYEES_VIEW;
    }

    private List<String> validateUserInput(String surname) {
        List<String> errors = new ArrayList<>();
        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.NAME_SURNAME, surname, errors);
        return errors;
    }
}
