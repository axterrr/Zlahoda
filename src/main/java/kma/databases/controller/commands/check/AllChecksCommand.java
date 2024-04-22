package kma.databases.controller.commands.check;

import kma.databases.constants.Attribute;
import kma.databases.constants.Page;
import kma.databases.controller.commands.Command;
import kma.databases.controller.utils.HttpWrapper;
import kma.databases.entities.Check;
import kma.databases.services.CheckService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AllChecksCommand implements Command {

    private final CheckService checkService;

    public AllChecksCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpWrapper httpWrapper) throws ServletException, IOException {
        List<Check> checks = checkService.getAllChecks();
        httpWrapper.getRequest().setAttribute(Attribute.CHECKS, checks);
        return Page.ALL_CHECKS_VIEW;
    }
}
