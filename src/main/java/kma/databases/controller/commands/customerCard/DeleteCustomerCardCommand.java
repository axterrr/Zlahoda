package kma.databases.controller.commands.customerCard;

import kma.databases.constants.Attribute;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.services.CustomerCardService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteCustomerCardCommand implements Command {

    private final CustomerCardService customerCardService;

    public DeleteCustomerCardCommand(CustomerCardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        String customerCardId = httpWrapper.getRequest().getParameter(Attribute.ID_CUSTOMER_CARD);
        customerCardService.deleteCustomerCard(customerCardId);
        redirectToAllCustomerCardsPageWithSuccessMessage(httpWrapper);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllCustomerCardsPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Customer card successfully deleted");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CUSTOMER_CARDS, urlParams);
    }
}
