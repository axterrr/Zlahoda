package kma.databases.controller.commands.auth;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.controller.utils.SessionManager;
import kma.databases.dto.CredentialsDto;
import kma.databases.entities.Employee;
import kma.databases.services.EmployeeService;
import kma.databases.validators.entities.CredentialsDtoValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PostLoginCommand implements Command {

    private final EmployeeService userService;

    public PostLoginCommand(EmployeeService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        HttpServletRequest request = httpWrapper.getRequest();
        HttpServletResponse response = httpWrapper.getResponse();
        HttpSession session = request.getSession();

        if (SessionManager.getInstance().isUserLoggedIn(session)) {
            RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), ServletPath.HOME);
            return RedirectionManager.REDIRECTION;
        }

        CredentialsDto credentialsDto = new CredentialsDto(request.getParameter(Attribute.PHONE), request.getParameter(Attribute.PASSWORD));
        List<String> errors = CredentialsDtoValidator.getInstance().validate(credentialsDto);

        if (!errors.isEmpty()) {
            addRequestAttributes(request, credentialsDto, errors);
            return Page.LOGIN_VIEW;
        }

        Optional<Employee> employee = userService.getEmployeeByCredentials(credentialsDto);
        if (employee.isPresent()) {
            SessionManager.getInstance().addUserToSession(session, employee.get());
            RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), ServletPath.HOME);
            return RedirectionManager.REDIRECTION;
        }
        errors.add("Login or Password is not correct");

        addRequestAttributes(request, credentialsDto, errors);
        return Page.LOGIN_VIEW;
    }

    private void addRequestAttributes(HttpServletRequest request, CredentialsDto credentialsDto, List<String> errors) {
        request.setAttribute(Attribute.LOGIN_USER, credentialsDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
