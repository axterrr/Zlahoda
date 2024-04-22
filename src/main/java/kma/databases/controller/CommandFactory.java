package kma.databases.controller;

import kma.databases.controller.commands.Command;

class CommandFactory {

    private CommandFactory() {}

    static Command getCommand(String commendKey) {
        return CommandEnum.getCommand(commendKey);
    }
}
