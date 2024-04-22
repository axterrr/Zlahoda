package kma.databases.controller.commands.customerCard;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.dto.CategoryDto;
import kma.databases.dto.CustomerCardDto;
import kma.databases.entities.CustomerCard;
import kma.databases.services.CustomerCardService;
import kma.databases.validators.entities.CategoryDtoValidator;
import kma.databases.validators.entities.CustomerCardDtoValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PostAddCustomerCardCommand implements Command {

    private final CustomerCardService customerCardService;

    public PostAddCustomerCardCommand(CustomerCardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        CustomerCardDto customerCardDto = getUserInput(httpWrapper.getRequest());
        List<String> errors = CustomerCardDtoValidator.getInstance().validate(customerCardDto);

        if (errors.isEmpty()) {
            customerCardService.createCustomerCard(customerCardDto);
            redirectToAllCustomerCardsPageWithSuccessMessage(httpWrapper);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(httpWrapper.getRequest(), customerCardDto, errors);
        return Page.ADD_UPDATE_CUSTOMER_CARD_VIEW;
    }

    private CustomerCardDto getUserInput(HttpServletRequest request) {
        return new CustomerCardDto.Builder()
                .setSurname(request.getParameter(Attribute.SURNAME))
                .setName(request.getParameter(Attribute.NAME))
                .setPatronymic(request.getParameter(Attribute.PATRONYMIC))
                .setPhoneNumber(request.getParameter(Attribute.PHONE))
                .setCity(request.getParameter(Attribute.CITY))
                .setStreet(request.getParameter(Attribute.STREET))
                .setZipCode(request.getParameter(Attribute.ZIP_CODE))
                .setPercent(request.getParameter(Attribute.PERCENT))
                .build();
    }

    private void redirectToAllCustomerCardsPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Customer card successfully added");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CUSTOMER_CARDS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, CustomerCardDto customerCardDto, List<String> errors) {
        request.setAttribute(Attribute.CUSTOMER_CARD_DTO, customerCardDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
