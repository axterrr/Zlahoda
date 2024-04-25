package kma.databases.controller.commands.customerCard;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.CustomerCard;
import kma.databases.services.CustomerCardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class GetUpdateCustomerCardCommand implements Command {

    private final CustomerCardService customerCardService;

    public GetUpdateCustomerCardCommand(CustomerCardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        HttpServletRequest request = httpWrapper.getRequest();
        String customerCardId = request.getParameter(Attribute.ID_CUSTOMER_CARD);
        Optional<CustomerCard> customerCard = customerCardService.getCustomerCardById(customerCardId);
        request.setAttribute(Attribute.CUSTOMER_CARD_DTO, customerCard.get());
        return Page.ADD_UPDATE_CUSTOMER_CARD_VIEW;
    }
}
