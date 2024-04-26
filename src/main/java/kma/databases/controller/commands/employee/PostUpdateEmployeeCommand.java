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

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
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
            String password = employeeDto.getPassword();
            byte[] salt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int iterations = 10000;
            int keyLength = 256;

            String hashedPassword = hashPassword(password.toCharArray(), salt, iterations, keyLength);
            employeeDto.setPassword(hashedPassword);

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

    private static String hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing a password", e);
        }
    }
}
