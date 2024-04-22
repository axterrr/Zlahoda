package kma.databases.controller.commands.storeProduct;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.StoreProduct;
import kma.databases.services.StoreProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AllStoreProductsCommand implements Command {

    private final StoreProductService storeProductService;

    public AllStoreProductsCommand(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        List<StoreProduct> storeProducts = storeProductService.getAllStoreProducts();
        httpWrapper.getRequest().setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        return Page.ALL_STORE_PRODUCTS_VIEW;
    }
}
