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

        if (httpRequest.getRequestURI().equals("/controller/login")) {
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
        return true;
    }

    private boolean isCashierPage(String requestURI) {
        return true;
    }

}
