package kma.databases.controller.commands.storeProduct;

import kma.databases.constants.Attribute;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.services.ProductService;
import kma.databases.services.StoreProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteStoreProductCommand implements Command {

    private final StoreProductService storeProductService;

    public DeleteStoreProductCommand(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        String storeProductId = httpWrapper.getRequest().getParameter(Attribute.ID_STORE_PRODUCT);
        storeProductService.deleteStoreProduct(storeProductId);
        redirectToAllStoreProductsPageWithSuccessMessage(httpWrapper);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllStoreProductsPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Store product successfully deleted");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_STORE_PRODUCTS, urlParams);
    }
}
