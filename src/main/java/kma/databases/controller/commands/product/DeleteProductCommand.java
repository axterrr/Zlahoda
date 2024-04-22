package kma.databases.controller.commands.product;

import kma.databases.constants.Attribute;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.services.EmployeeService;
import kma.databases.services.ProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteProductCommand implements Command {

    private final ProductService productService;

    public DeleteProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        Long productId = Long.parseLong(httpWrapper.getRequest().getParameter(Attribute.ID_PRODUCT));
        productService.deleteProduct(productId);
        redirectToAllProductsPageWithSuccessMessage(httpWrapper);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllProductsPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Product successfully deleted");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_PRODUCTS, urlParams);
    }
}
