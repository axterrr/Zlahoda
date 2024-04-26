package kma.databases.controller.commands.check;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.services.CheckService;
import kma.databases.services.EmployeeService;
import kma.databases.services.ProductService;
import kma.databases.validators.fields.AbstractFieldValidatorHandler;
import kma.databases.validators.fields.FieldValidatorKey;
import kma.databases.validators.fields.FieldValidatorsChainGenerator;

import javax.servlet.ServletException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTotalSoldProductPerPeriodCommand implements Command {

    private final CheckService checkService;

    public CountTotalSoldProductPerPeriodCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        String productId = httpWrapper.getRequest().getParameter(Attribute.PRODUCT);
        String from = httpWrapper.getRequest().getParameter(Attribute.DATE_FROM);
        String to = httpWrapper.getRequest().getParameter(Attribute.DATE_TO);
        List<String> errors = validateUserInput(from, to);
        Map<String, String> urlParams;

        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CHECKS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        Long res = 0L;
        res += checkService.getTotalProductAmountByDate(Long.valueOf(productId), LocalDate.parse(from), LocalDate.parse(to).plusDays(1));

        httpWrapper.getRequest().setAttribute(Attribute.TOTAL_AMOUNT, res);
        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, EmployeeService.getInstance().getAllEmployeesCashiers());
        httpWrapper.getRequest().setAttribute(Attribute.PRODUCTS, ProductService.getInstance().getAllProducts());

        System.out.println(res);
        return Page.ALL_CHECKS_VIEW;
    }

    private List<String> validateUserInput(String from, String to) {
        List<String> errors = new ArrayList<>();
        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.DATE, from, errors);
        fieldValidator.validateField(FieldValidatorKey.DATE, to, errors);
        return errors;
    }
}
