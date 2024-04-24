package kma.databases.controller.commands.storeProduct;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.StoreProduct;
import kma.databases.services.StoreProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllPromotionalStoreProductsCommand implements Command {

    private final StoreProductService storeProductService;

    public AllPromotionalStoreProductsCommand(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        String isPromotional = httpWrapper.getRequest().getParameter(Attribute.IS_PROMOTIONAL);
        String orderBy = httpWrapper.getRequest().getParameter(Attribute.ORDER_BY);

        List<StoreProduct> storeProducts = new ArrayList<>();

        if(isPromotional.equals("true")) {
         if(orderBy.equals("amount"))
             storeProducts = storeProductService.getPromotionalStoreProductsOrderedByAmount();
         if(orderBy.equals("name"))
             storeProducts = storeProductService.getPromotionalStoreProductsOrderedByName();
        }
        if(isPromotional.equals("false")) {
            if(orderBy.equals("amount"))
                storeProducts = storeProductService.getNotPromotionalStoreProductsOrderedByAmount();
            if(orderBy.equals("name"))
                storeProducts = storeProductService.getNotPromotionalStoreProductsOrderedByName();
        }

        httpWrapper.getRequest().setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        return Page.ALL_STORE_PRODUCTS_VIEW;
    }
}
