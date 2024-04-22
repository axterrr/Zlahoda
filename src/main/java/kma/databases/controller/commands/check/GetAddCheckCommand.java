package kma.databases.controller.commands.check;

import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;

import javax.servlet.ServletException;
import java.io.IOException;

public class GetAddCheckCommand implements Command {

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        return Page.ADD_UPDATE_CHECK_VIEW;
    }
}
