package kma.databases.controller.commands.employee;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.dto.EmployeeDto;
import kma.databases.entities.Role;
import kma.databases.services.EmployeeService;
import kma.databases.validators.entities.EmployeeDtoValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUpdateEmployeeCommand implements Command {

    private final EmployeeService employeeService;

    public PostUpdateEmployeeCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        EmployeeDto employeeDto = getUserInput(httpWrapper.getRequest());
        List<String> errors = EmployeeDtoValidator.getInstance().validate(employeeDto);

        if (errors.isEmpty()) {
            employeeService.updateEmployee(employeeDto);
            redirectToAllEmployeesPageWithSuccessMessage(httpWrapper);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(httpWrapper.getRequest(), employeeDto, errors);
        return Page.ADD_UPDATE_EMPLOYEE_VIEW;
    }

    private EmployeeDto getUserInput(HttpServletRequest request) {
        return new EmployeeDto.Builder()
                .setId(request.getParameter(Attribute.ID_EMPLOYEE))
                .setSurname(request.getParameter(Attribute.SURNAME))
                .setName(request.getParameter(Attribute.NAME))
                .setPatronymic(request.getParameter(Attribute.PATRONYMIC))
                .setRole(Role.getRole(request.getParameter(Attribute.ROLE)))
                .setSalary(request.getParameter(Attribute.SALARY))
                .setDateOfBirth(request.getParameter(Attribute.DATE_OF_BIRTH))
                .setPhoneNumber(request.getParameter(Attribute.PHONE))
                .setCity(request.getParameter(Attribute.CITY))
                .setStreet(request.getParameter(Attribute.STREET))
                .setZipCode(request.getParameter(Attribute.ZIP_CODE))
                .setPassword(request.getParameter(Attribute.PASSWORD))
                .setConfirmPassword(request.getParameter(Attribute.CONFIRM_PASSWORD))
                .build();
    }

    private void redirectToAllEmployeesPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Employee successfully updated");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_EMPLOYEES, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, EmployeeDto employeeDto, List<String> errors) {
        request.setAttribute(Attribute.EMPLOYEE_DTO, employeeDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
