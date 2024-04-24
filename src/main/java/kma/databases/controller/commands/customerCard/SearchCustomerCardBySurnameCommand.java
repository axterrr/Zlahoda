package kma.databases.controller.commands.customerCard;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.entities.CustomerCard;
import kma.databases.entities.Employee;
import kma.databases.services.CustomerCardService;
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

public class SearchCustomerCardBySurnameCommand implements Command {

    private final CustomerCardService customerCardService;

    public SearchCustomerCardBySurnameCommand(CustomerCardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        String surname = httpWrapper.getRequest().getParameter(Attribute.SURNAME);
        List<String> errors = validateUserInput(surname);
        Map<String, String> urlParams;

        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CUSTOMER_CARDS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        List<CustomerCard> customers = customerCardService.searchCustomerCardBySurname(surname);

        if (customers.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, "Customer card is not found");
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CUSTOMER_CARDS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        httpWrapper.getRequest().setAttribute(Attribute.CUSTOMER_CARDS, customers);
        return Page.ALL_CUSTOMER_CARDS_VIEW;
    }

    private List<String> validateUserInput(String surname) {
        List<String> errors = new ArrayList<>();
        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.NAME_SURNAME, surname, errors);
        return errors;
    }
}
