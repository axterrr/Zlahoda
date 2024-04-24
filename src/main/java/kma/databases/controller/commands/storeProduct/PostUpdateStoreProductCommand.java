package kma.databases.controller.commands.storeProduct;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.dto.StoreProductDto;
import kma.databases.services.StoreProductService;
import kma.databases.validators.entities.StoreProductDtoValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUpdateStoreProductCommand implements Command {

    private final StoreProductService storeProductService;

    public PostUpdateStoreProductCommand(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        StoreProductDto storeProductDto = getUserInput(httpWrapper.getRequest());
        List<String> errors = StoreProductDtoValidator.getInstance().validate(storeProductDto);

        if (errors.isEmpty()) {
            storeProductService.updateStoreProduct(storeProductDto);
            redirectToAllStoreProductsPageWithSuccessMessage(httpWrapper);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(httpWrapper.getRequest(), storeProductDto, errors);
        return Page.ADD_UPDATE_STORE_PRODUCT_VIEW;
    }

    private StoreProductDto getUserInput(HttpServletRequest request) {
        return new StoreProductDto.Builder()
                .setUPC(request.getParameter(Attribute.ID_STORE_PRODUCT))
                //.setPromUPC(request.getParameter(Attribute.PROMOTIONAL_UPC))
                //.setProductId(Long.parseLong(request.getParameter(Attribute.ID_PRODUCT)))
                .setAmount(request.getParameter(Attribute.NUMBER))
                .setPrice(request.getParameter(Attribute.PRICE))
                .setPromotional(request.getParameter(Attribute.IS_PROMOTIONAL))
                .build();
    }

    private void redirectToAllStoreProductsPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Store product successfully updated");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_STORE_PRODUCTS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, StoreProductDto storeProductDto, List<String> errors) {
        request.setAttribute(Attribute.STORE_PRODUCT_DTO, storeProductDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
