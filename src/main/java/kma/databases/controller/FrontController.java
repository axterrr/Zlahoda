package kma.databases.controller;

import kma.databases.constants.Attribute;
import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.CommandKeyGenerator;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = { "/controller/*" }, loadOnStartup = 1)
public class FrontController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        String commandKey = CommandKeyGenerator.generateCommandKeyFromRequest(request);
        Command command = CommandFactory.getCommand(commandKey);
        try {
            String commandResultedResource = command.execute(httpWrapper);
            forwardToCommandResultedPage(httpWrapper, commandResultedResource);
        } catch (ServiceException ex) {
            redirectToHomePageWithErrorMessage(httpWrapper, ex);
        }
    }

    private void forwardToCommandResultedPage(HttpWrapper httpWrapper, String resultedRedirectResource) throws ServletException, IOException {
        if (!resultedRedirectResource.contains(RedirectionManager.REDIRECTION)) {
            httpWrapper.getRequest().getRequestDispatcher(resultedRedirectResource)
                    .forward(httpWrapper.getRequest(), httpWrapper.getResponse());
        }
    }

    private void redirectToHomePageWithErrorMessage(HttpWrapper httpWrapper, ServiceException ex) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.ERROR, ex.getMessage());
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.HOME, urlParams);
    }
}
