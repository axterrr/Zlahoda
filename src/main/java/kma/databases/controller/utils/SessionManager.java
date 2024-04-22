package kma.databases.controller.utils;

import kma.databases.constants.Attribute;
import kma.databases.entities.Employee;

import javax.servlet.http.HttpSession;

public class SessionManager {

    private SessionManager() {}

    private static final class Holder {
        static final SessionManager INSTANCE = new SessionManager();
    }

    public static SessionManager getInstance() {
        return Holder.INSTANCE;
    }

    public boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute(Attribute.EMPLOYEE) != null;
    }

    public void addUserToSession(HttpSession session, Employee user) {
        session.setAttribute(Attribute.EMPLOYEE, user);
    }

    public Employee getUserFromSession(HttpSession session) {
        return (Employee) session.getAttribute(Attribute.EMPLOYEE);
    }

    public void invalidateSession(HttpSession session) {
        if (session != null && session.getAttribute(Attribute.EMPLOYEE) != null) {
            session.invalidate();
        }
    }
}
