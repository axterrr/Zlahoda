package kma.databases.controller.commands.auth;

import kma.databases.constants.ServletPath;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.controller.utils.RedirectionManager;
import kma.databases.controller.utils.SessionManager;

import javax.servlet.ServletException;
import java.io.IOException;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        SessionManager.getInstance().invalidateSession(httpWrapper.getRequest().getSession());
        RedirectionManager.getInstance().redirect(httpWrapper, ServletPath.HOME);
        return RedirectionManager.REDIRECTION;
    }
}
