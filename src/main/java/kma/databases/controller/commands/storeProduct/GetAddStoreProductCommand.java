package kma.databases.controller.commands.storeProduct;

import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class GetAddStoreProductCommand implements Command {

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        return Page.ADD_UPDATE_STORE_PRODUCT_VIEW;
    }
}
