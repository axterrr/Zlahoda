package kma.databases.controller.commands.storeProduct;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.Product;
import kma.databases.entities.StoreProduct;
import kma.databases.services.ProductService;
import kma.databases.services.StoreProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAddStoreProductCommand implements Command {

    private final ProductService productService;

    public GetAddStoreProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        List<Product> products = productService.getAllProducts();
        httpWrapper.getRequest().setAttribute(Attribute.PRODUCTS, products);
        return Page.ADD_UPDATE_STORE_PRODUCT_VIEW;
    }
}
