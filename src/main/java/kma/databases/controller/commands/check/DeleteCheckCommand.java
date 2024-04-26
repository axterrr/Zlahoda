package kma.databases.controller.commands.check;

import kma.databases.constants.Attribute;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.services.CheckService;
import kma.databases.services.EmployeeService;
import kma.databases.services.ProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteCheckCommand implements Command {

    private final CheckService checkService;

    public DeleteCheckCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        String checkId = httpWrapper.getRequest().getParameter(Attribute.ID_CHECK);
        checkService.deleteCheck(checkId);
        redirectToAllChecksPageWithSuccessMessage(httpWrapper);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllChecksPageWithSuccessMessage(HttpWrapper httpWrapper) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Check successfully deleted");
        httpWrapper.getRequest().setAttribute(Attribute.EMPLOYEES, EmployeeService.getInstance().getAllEmployeesCashiers());
        httpWrapper.getRequest().setAttribute(Attribute.PRODUCTS, ProductService.getInstance().getAllProducts());
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CHECKS, urlParams);
    }
}
