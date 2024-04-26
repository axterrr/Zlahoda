package kma.databases.controller.commands.check;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.controller.utils.SessionManager;
import kma.databases.dto.CategoryDto;
import kma.databases.dto.CheckDto;
import kma.databases.dto.CustomerCardDto;
import kma.databases.dto.SaleDto;
import kma.databases.entities.Check;
import kma.databases.entities.CustomerCard;
import kma.databases.entities.Sale;
import kma.databases.entities.StoreProduct;
import kma.databases.services.CheckService;
import kma.databases.services.CustomerCardService;
import kma.databases.services.SaleService;
import kma.databases.services.StoreProductService;
import kma.databases.validators.entities.CheckDtoValidator;
import kma.databases.validators.entities.CustomerCardDtoValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class PostAddCheckCommand implements Command {
    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {

        List<String> errors = new ArrayList<>();
        if(httpWrapper.getRequest().getParameterValues(Attribute.STORE_PRODUCTS_ARRAY) == null
            || httpWrapper.getRequest().getParameterValues(Attribute.AMOUNT_ARRAY) == null) {
                errors.add("Choose some products!");
                addRequestAttributes(httpWrapper.getRequest(), errors);
                return Page.ADD_UPDATE_CHECK_VIEW;
        }

        CheckDto checkDto = getUserInput(httpWrapper.getRequest());
        errors = CheckDtoValidator.getInstance().validate(checkDto);

        if (errors.isEmpty()) {
            CheckService.getInstance().createCheck(checkDto);

            for(Sale s : checkDto.getSales()) {
                SaleDto.Builder builder = new SaleDto.Builder()
                        .setCheck(new Check.Builder().setNumber(checkDto.getNumber()).build())
                        .setPrice(s.getPrice().toString())
                        .setStoreProduct(s.getStoreProduct())
                        .setProductsNumber(s.getProductsNumber().toString());
                SaleService.getInstance().createSale(builder.build());

                String upc = s.getStoreProduct().getUpc();
                StoreProductService.getInstance().updateStoreProductAmount(upc,
                StoreProductService.getInstance().getStoreProductById(upc).get().getAmount() - s.getProductsNumber());
            }

            redirectToAllChecksPageWithSuccessMessage(httpWrapper);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(httpWrapper.getRequest(), errors);
        return Page.ADD_UPDATE_CHECK_VIEW;
    }

    private CheckDto getUserInput(HttpServletRequest request) {

        String CustomerCardNumber = request.getParameter(Attribute.CUSTOMER_CARD);
        String[] storeProductsNumbers = request.getParameterValues(Attribute.STORE_PRODUCTS_ARRAY);
        String[] storeProductsAmounts = request.getParameterValues(Attribute.AMOUNT_ARRAY);

        CheckDto.Builder builder = new CheckDto.Builder()
                .setCustomerCard(new CustomerCard.Builder().setNumber(CustomerCardNumber).build())
                .setEmployee(SessionManager.getInstance().getUserFromSession(request.getSession()))
                .setPrintDate(LocalDateTime.now().toString());

        BigDecimal totalSum = new BigDecimal(0);
        List<Sale> saleList = new ArrayList<>();

        for (int i=0; i<storeProductsNumbers.length; i++) {

            BigDecimal price = StoreProductService.getInstance()
                    .getStoreProductById(storeProductsNumbers[i]).get().getPrice();

            saleList.add(new Sale.Builder()
                                .setStoreProduct(new StoreProduct.Builder().setUPC(storeProductsNumbers[i]).build())
                                .setProductsNumber(Long.valueOf(storeProductsAmounts[i]))
                                .setPrice(price)
                                .build());

            totalSum = totalSum.add(price.multiply(new BigDecimal(storeProductsAmounts[i])));
        }

        builder.setTotalSum(totalSum.toString())
                .setVat(String.valueOf(totalSum.multiply(BigDecimal.valueOf(0.2))))
                .setSales(saleList);

        return builder.build();
    }

    private void redirectToAllChecksPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Check successfully added");
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CHECKS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, List<String> errors) {
        request.setAttribute(Attribute.CUSTOMER_CARDS, CustomerCardService.getInstance().getAllCustomerCards());
        request.setAttribute(Attribute.STORE_PRODUCTS, StoreProductService.getInstance().getAllStoreProducts());
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
