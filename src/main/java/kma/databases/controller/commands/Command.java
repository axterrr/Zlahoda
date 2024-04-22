package kma.databases.controller.commands;

import kma.databases.controller.utils.HttpWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    String execute(HttpWrapper httpWrapper) throws ServletException, IOException;

}
