package kma.databases.controller.commands;

import kma.databases.constants.Page;
import kma.databases.controller.utils.HttpWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand implements Command {
    @Override
    public String execute(HttpWrapper httpWrapper) {
        return Page.HOME_VIEW;
    }
}
