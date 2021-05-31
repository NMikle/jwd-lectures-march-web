package com.epam.jwd.web.command;

public enum AppCommand {
    MAIN_PAGE(ShowMainPageCommand.INSTANCE),
    SHOW_USERS(ShowUsersPage.INSTANCE),
    DEFAULT(ShowMainPageCommand.INSTANCE);

    private final Command command;

    AppCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    static AppCommand of(String name) {
        for (AppCommand command : values()) {
            if (command.name().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return DEFAULT;
    }
}
