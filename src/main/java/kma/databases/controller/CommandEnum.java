package kma.databases.controller;

import kma.databases.controller.commands.*;
import kma.databases.controller.commands.auth.*;
import kma.databases.controller.commands.category.*;
import kma.databases.controller.commands.check.*;
import kma.databases.controller.commands.customerCard.*;
import kma.databases.controller.commands.employee.*;
import kma.databases.controller.commands.product.*;
import kma.databases.controller.commands.storeProduct.*;
import kma.databases.services.*;

enum CommandEnum {

    HOME ("GET:", new HomeCommand()),
    PAGE_NOT_FOUND ("GET:pageNotFound", new PageNotFoundCommand()),

    GET_LOGIN ("GET:login", new GetLoginCommand()),
    LOGOUT ("GET:logout", new LogoutCommand()),
    POST_LOGIN ("POST:login", new PostLoginCommand(EmployeeService.getInstance())),

    ALL_CATEGORIES ("GET:categories", new AllCategoriesCommand(CategoryService.getInstance())),
    GET_ADD_CATEGORY ("GET:categories/addCategory", new GetAddCategoryCommand()),
    POST_ADD_CATEGORY ("POST:categories/addCategory", new PostAddCategoryCommand(CategoryService.getInstance())),
    GET_UPDATE_CATEGORY ("GET:categories/updateCategory", new GetUpdateCategoryCommand(CategoryService.getInstance())),
    POST_UPDATE_CATEGORY ("POST:categories/updateCategory", new PostUpdateCategoryCommand(CategoryService.getInstance())),
    DELETE_CATEGORY ("GET:categories/deleteCategory", new DeleteCategoryCommand(CategoryService.getInstance())),

    ALL_CHECKS ("GET:checks", new AllChecksCommand(CheckService.getInstance())),
    GET_ADD_CHECK ("GET:checks/addCheck", new GetAddCheckCommand(StoreProductService.getInstance())),
    POST_ADD_CHECK ("POST:checks/addCheck", new PostAddCheckCommand()),
    DELETE_CHECK ("GET:checks/deleteCheck", new DeleteCheckCommand(CheckService.getInstance())),
    SEARCH_CHECK_BY_CASHIER_AND_DATE ("POST:checks/search", new SearchChecksByCashierPerPeriodCommand(CheckService.getInstance())),
    COUNT_TOTAL_SUM_BY_CASHIER_AND_DATE ("POST:checks/sum", new CountTotalSumByCashierPerPeriodCommand(CheckService.getInstance())),
    COUNT_TOTAL_SOLD_PRODUCT_AMOUNT_BY_DATE ("POST:checks/amount", new CountTotalSoldProductPerPeriodCommand(CheckService.getInstance())),
    SEARCH_CHECK_BY_NUMBER ("POST:checks/number", new SearchChecksByNumberCommand(CheckService.getInstance())),

    ALL_CUSTOMER_CARDS ("GET:customerCards", new AllCustomerCardsCommand(CustomerCardService.getInstance())),
    GET_ADD_CUSTOMER_CARD ("GET:customerCards/addCustomerCard", new GetAddCustomerCardCommand()),
    POST_ADD_CUSTOMER_CARD ("POST:customerCards/addCustomerCard", new PostAddCustomerCardCommand(CustomerCardService.getInstance())),
    GET_UPDATE_CUSTOMER_CARD ("GET:customerCards/updateCustomerCard", new GetUpdateCustomerCardCommand(CustomerCardService.getInstance())),
    POST_UPDATE_CUSTOMER_CARD ("POST:customerCards/updateCustomerCard", new PostUpdateCustomerCardCommand(CustomerCardService.getInstance())),
    DELETE_CUSTOMER_CARD ("GET:customerCards/deleteCustomerCard", new DeleteCustomerCardCommand(CustomerCardService.getInstance())),
    SEARCH_CUSTOMER_CARD_BY_PERCENT ("POST:customerCards/percent", new SearchCustomerCardByPercentCommand(CustomerCardService.getInstance())),
    SEARCH_CUSTOMER_CARD_BY_SURNAME ("POST:customerCards/surname", new SearchCustomerCardBySurnameCommand(CustomerCardService.getInstance())),

    ALL_EMPLOYEES ("GET:employees", new AllEmployeesCommand(EmployeeService.getInstance())),
    GET_ADD_EMPLOYEE ("GET:employees/addEmployee", new GetAddEmployeeCommand()),
    POST_ADD_EMPLOYEE ("POST:employees/addEmployee", new PostAddEmployeeCommand(EmployeeService.getInstance())),
    GET_UPDATE_EMPLOYEE ("GET:employees/updateEmployee", new GetUpdateEmployeeCommand(EmployeeService.getInstance())),
    POST_UPDATE_EMPLOYEE ("POST:employees/updateEmployee", new PostUpdateEmployeeCommand(EmployeeService.getInstance())),
    DELETE_EMPLOYEE ("GET:employees/deleteEmployee", new DeleteEmployeeCommand(EmployeeService.getInstance())),
    ALL_EMPLOYEES_BY_ROLE ("POST:employees/role", new AllEmployeesByRoleCommand(EmployeeService.getInstance())),
    SEARCH_EMPLOYEE_BY_SURNAME("POST:employees/surname", new SearchEmployeeBySurnameCommand(EmployeeService.getInstance())),

    ALL_PRODUCTS ("GET:products", new AllProductsCommand(ProductService.getInstance(), CategoryService.getInstance())),
    GET_ADD_PRODUCT ("GET:products/addProduct", new GetAddProductCommand(CategoryService.getInstance())),
    POST_ADD_PRODUCT ("POST:products/addProduct", new PostAddProductCommand(ProductService.getInstance())),
    GET_UPDATE_PRODUCT ("GET:products/updateProduct", new GetUpdateProductCommand(ProductService.getInstance(), CategoryService.getInstance())),
    POST_UPDATE_PRODUCT ("POST:products/updateProduct", new PostUpdateProductCommand(ProductService.getInstance())),
    DELETE_PRODUCT ("GET:products/deleteProduct", new DeleteProductCommand(ProductService.getInstance())),
    SEARCH_PRODUCT_BY_CATEGORY ("POST:products/category", new SearchProductByCategoryCommand(ProductService.getInstance(), CategoryService.getInstance())),
    SEARCH_PRODUCT_BY_NAME ("POST:products/name", new SearchProductByNameCommand(ProductService.getInstance(), CategoryService.getInstance())),

    ALL_STORE_PRODUCTS ("GET:storeProducts", new AllStoreProductsCommand(StoreProductService.getInstance())),
    ALL_STORE_PRODUCTS_POST ("POST:storeProducts", new AllStoreProductsCommand(StoreProductService.getInstance())),
    GET_ADD_STORE_PRODUCT ("GET:storeProducts/addStoreProduct", new GetAddStoreProductCommand(ProductService.getInstance())),
    POST_ADD_STORE_PRODUCT ("POST:storeProducts/addStoreProduct", new PostAddStoreProductCommand(StoreProductService.getInstance())),
    GET_UPDATE_STORE_PRODUCT ("GET:storeProducts/updateStoreProduct", new GetUpdateStoreProductCommand(StoreProductService.getInstance())),
    POST_UPDATE_STORE_PRODUCT ("POST:storeProducts/updateStoreProduct", new PostUpdateStoreProductCommand(StoreProductService.getInstance())),
    DELETE_STORE_PRODUCT ("GET:storeProducts/deleteStoreProduct", new DeleteStoreProductCommand(StoreProductService.getInstance())),
    SEARCH_STORE_PRODUCT_BY_UPC ("POST:storeProducts/upc", new SearchStoreProductByUPCCommand(StoreProductService.getInstance()));

    private String key;
    private Command command;

    CommandEnum(String key, Command command) {
        this.key = key;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public static Command getCommand(String key) {
        for (CommandEnum command : CommandEnum.values()) {
            if (command.getKey().equals(key)) {
                return command.getCommand();
            }
        }
        return PAGE_NOT_FOUND.getCommand();
    }
}
