package kma.databases.controller.commands.storeProduct;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.entities.Product;
import kma.databases.entities.StoreProduct;
import kma.databases.services.StoreProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class GetUpdateStoreProductCommand implements Command {

    private final StoreProductService storeProductService;

    public GetUpdateStoreProductCommand(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        HttpServletRequest request = httpWrapper.getRequest();
        String storeProductId = request.getParameter(Attribute.ID_STORE_PRODUCT);
        Optional<StoreProduct> storeProduct = storeProductService.getStoreProductById(storeProductId);
        request.setAttribute(Attribute.STORE_PRODUCT_DTO, storeProduct.get());
        return Page.ADD_UPDATE_STORE_PRODUCT_VIEW;
    }
}
