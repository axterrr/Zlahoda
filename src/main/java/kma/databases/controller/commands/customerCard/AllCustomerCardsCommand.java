package kma.databases.controller.commands.customerCard;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.CustomerCard;
import kma.databases.services.CustomerCardService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AllCustomerCardsCommand implements Command {

    private final CustomerCardService customerCardService;

    public AllCustomerCardsCommand(CustomerCardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        List<CustomerCard> cards = customerCardService.getAllCustomerCards();
        httpWrapper.getRequest().setAttribute(Attribute.CUSTOMER_CARDS, cards);
        return Page.ALL_CUSTOMER_CARDS_VIEW;
    }
}
