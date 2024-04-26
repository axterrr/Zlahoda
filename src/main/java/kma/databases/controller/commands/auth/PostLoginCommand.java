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

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
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

        String password = request.getParameter(Attribute.PASSWORD);
        byte[] salt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int iterations = 10000;
        int keyLength = 256;

        String hashedPassword = hashPassword(password.toCharArray(), salt, iterations, keyLength);

        CredentialsDto credentialsDto = new CredentialsDto(request.getParameter(Attribute.PHONE), hashedPassword);
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
