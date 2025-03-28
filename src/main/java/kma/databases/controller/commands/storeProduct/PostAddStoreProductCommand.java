package kma.databases.controller.commands.storeProduct;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.dto.StoreProductDto;
import kma.databases.dto.ProductDto;
import kma.databases.entities.Product;
import kma.databases.entities.Role;
import kma.databases.entities.StoreProduct;
import kma.databases.services.ProductService;
import kma.databases.services.StoreProductService;
import kma.databases.validators.entities.StoreProductDtoValidator;
import kma.databases.validators.entities.ProductDtoValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PostAddStoreProductCommand implements Command {

    private final StoreProductService storeProductService;

    public PostAddStoreProductCommand(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        StoreProductDto storeProductDto = getUserInput(httpWrapper.getRequest());
        List<String> errors = StoreProductDtoValidator.getInstance().validate(storeProductDto);

        if (errors.isEmpty()) {

            Optional<StoreProduct> promotional = storeProductService.getPromotionalStoreProductByProductId(
                    storeProductDto.getProduct().getId());
            Optional<StoreProduct> notPromotional = storeProductService.getNotPromotionalStoreProductByProductId(
                    storeProductDto.getProduct().getId());

            if (storeProductDto.isPromotional().equals("true") && promotional.isPresent()) {
                errors.add("This promotional product is already present");
                addRequestAttributes(httpWrapper.getRequest(), storeProductDto, errors);
                return Page.ADD_UPDATE_STORE_PRODUCT_VIEW;
            }

            if (storeProductDto.isPromotional().equals("false") && notPromotional.isPresent()) {
                errors.add("This (not promotional) product is already present");
                addRequestAttributes(httpWrapper.getRequest(), storeProductDto, errors);
                return Page.ADD_UPDATE_STORE_PRODUCT_VIEW;
            }

            storeProductService.createStoreProduct(storeProductDto);

            if(storeProductDto.isPromotional().equals("true") && notPromotional.isPresent()) {
                storeProductService.addPromotionalProduct(notPromotional.get().getUpc(), storeProductDto.getUpc());
            }

            if(storeProductDto.isPromotional().equals("false") && promotional.isPresent()) {
                storeProductService.addPromotionalProduct(storeProductDto.getUpc(), promotional.get().getUpc());
            }

            redirectToAllStoreProductsPageWithSuccessMessage(httpWrapper);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(httpWrapper.getRequest(), storeProductDto, errors);
        return Page.ADD_UPDATE_STORE_PRODUCT_VIEW;
    }

    private StoreProductDto getUserInput(HttpServletRequest request) {
        return new StoreProductDto.Builder()
                .setProm(new StoreProduct.Builder().setUPC(request.getParameter(Attribute.PROMOTIONAL_UPC)).build())
                .setProduct(new Product.Builder().setId(Long.parseLong(request.getParameter(Attribute.PRODUCT))).build())
                .setAmount(request.getParameter(Attribute.NUMBER))
                .setPrice(request.getParameter(Attribute.PRICE))
                .setPromotional(request.getParameter(Attribute.IS_PROMOTIONAL))
                .build();
    }

    private void redirectToAllStoreProductsPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Store product successfully added");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_STORE_PRODUCTS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, StoreProductDto storeProductDto, List<String> errors) {
        request.setAttribute(Attribute.STORE_PRODUCT_DTO, storeProductDto);
        request.setAttribute(Attribute.PRODUCTS, ProductService.getInstance().getAllProducts());
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
