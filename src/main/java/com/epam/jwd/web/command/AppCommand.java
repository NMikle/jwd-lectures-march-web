package com.epam.jwd.web.command;

import com.epam.jwd.web.command.auth.LoginCommand;
import com.epam.jwd.web.command.auth.LogoutCommand;
import com.epam.jwd.web.command.page.ShowLoginPage;
import com.epam.jwd.web.command.page.ShowMainPageCommand;
import com.epam.jwd.web.command.page.ShowUsersPage;

public enum AppCommand {
    MAIN_PAGE(ShowMainPageCommand.INSTANCE),
    SHOW_USERS(ShowUsersPage.INSTANCE),
    LOGIN(LoginCommand.INSTANCE),
    SHOW_LOGIN(ShowLoginPage.INSTANCE),
    LOGOUT(LogoutCommand.INSTANCE),
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
