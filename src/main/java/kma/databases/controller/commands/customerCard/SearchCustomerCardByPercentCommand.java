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
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCustomerCardByPercentCommand implements Command {


    private final CustomerCardService customerCardService;

    public SearchCustomerCardByPercentCommand(CustomerCardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        String from = httpWrapper.getRequest().getParameter(Attribute.PERCENT_FROM);
        String to = httpWrapper.getRequest().getParameter(Attribute.PERCENT_TO);
        List<String> errors = validateUserInput(from, to);
        Map<String, String> urlParams;

        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CUSTOMER_CARDS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        List<CustomerCard> cards = customerCardService.searchCustomerCardByPercent(Long.parseLong(from), Long.parseLong(to));

        if (cards.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, "Customer card is not found");
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CUSTOMER_CARDS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEE, cards);
        return Page.ALL_CUSTOMER_CARDS_VIEW;
    }

    private List<String> validateUserInput(String from, String to) {
        List<String> errors = new ArrayList<>();
        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.PERCENT, from, errors);
        fieldValidator.validateField(FieldValidatorKey.PERCENT, to, errors);
        return errors;
    }
}
