package kma.databases.controller.commands.check;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.services.CustomerCardService;
import kma.databases.services.ProductService;
import kma.databases.services.StoreProductService;

import javax.servlet.ServletException;
import java.io.IOException;

public class GetAddCheckCommand implements Command {

    private final StoreProductService storeProductService;

    public GetAddCheckCommand(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        httpWrapper.getRequest().setAttribute(Attribute.CUSTOMER_CARDS, CustomerCardService.getInstance().getAllCustomerCards());
        httpWrapper.getRequest().setAttribute(Attribute.STORE_PRODUCTS, storeProductService.getAllStoreProducts());
        return Page.ADD_UPDATE_CHECK_VIEW;
    }
}
