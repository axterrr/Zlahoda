package kma.databases.controller.filters;

import kma.databases.constants.Attribute;
import kma.databases.constants.ServletPath;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.controller.utils.SessionManager;
import kma.databases.entities.Employee;
import kma.databases.entities.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = { "/controller/*" })
public class UnauthorizedAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (httpRequest.getRequestURI().equals("/controller/login") || httpRequest.getRequestURI().equals("/controller/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Employee user = SessionManager.getInstance().getUserFromSession(httpRequest.getSession());

        if ((user == null) || !isUserAuthorizedForResource(httpRequest.getRequestURI(), user)) {
            HttpWrapper httpWrapper = new HttpWrapper(httpRequest, httpResponse);
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, "Unauthorized access to the resource");
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.HOME, urlParams);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}

    private boolean isUserAuthorizedForResource(String servletPath, Employee user) {
        return (isManagerPage(servletPath) && user.getRole().equals(Role.MANAGER))
                || (isCashierPage(servletPath) && user.getRole().equals(Role.CASHIER));
    }

    private boolean isManagerPage(String requestURI) {
        return !requestURI.startsWith("/controller/checks/addCheck");
    }

    private boolean isCashierPage(String requestURI) {
        return !requestURI.startsWith("/controller/categories")
            && !requestURI.startsWith("/controller/checks/deleteCheck")
            && !requestURI.startsWith("/controller/checks/sum")
            && !requestURI.startsWith("/controller/checks/amount")
            && !requestURI.startsWith("/controller/customerCards/deleteCustomerCard")
            && !requestURI.startsWith("/controller/customerCards/percent")
            && !requestURI.startsWith("/controller/employees/")
            && !requestURI.startsWith("/controller/products/addProduct")
            && !requestURI.startsWith("/controller/products/updateProduct")
            && !requestURI.startsWith("/controller/products/deleteProduct")
            && !requestURI.startsWith("/controller/storeProducts/addStoreProduct")
            && !requestURI.startsWith("/controller/storeProducts/updateStoreProduct")
            && !requestURI.startsWith("/controller/storeProducts/deleteStoreProduct")
            && !requestURI.startsWith("/controller/products/customerFavourite")
            && !requestURI.startsWith("/controller/checks/promotionalCheck");
    }

}
