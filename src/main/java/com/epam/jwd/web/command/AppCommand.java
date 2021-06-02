package com.epam.jwd.web.command;

import com.epam.jwd.web.command.auth.LoginCommand;
import com.epam.jwd.web.command.auth.LogoutCommand;
import com.epam.jwd.web.command.page.ShowErrorPage;
import com.epam.jwd.web.command.page.ShowLoginPage;
import com.epam.jwd.web.command.page.ShowMainPageCommand;
import com.epam.jwd.web.command.page.ShowUsersPage;
import com.epam.jwd.web.model.Role;

import java.util.Arrays;
import java.util.List;

import static com.epam.jwd.web.model.Role.ADMIN;
import static com.epam.jwd.web.model.Role.UNAUTHORIZED;
import static com.epam.jwd.web.model.Role.USER;

public enum AppCommand {
    MAIN_PAGE(ShowMainPageCommand.INSTANCE),
    SHOW_USERS(ShowUsersPage.INSTANCE, ADMIN),
    LOGIN(LoginCommand.INSTANCE, UNAUTHORIZED),
    SHOW_LOGIN(ShowLoginPage.INSTANCE, UNAUTHORIZED),
    LOGOUT(LogoutCommand.INSTANCE, USER, ADMIN),
    ERROR(ShowErrorPage.INSTANCE),
    DEFAULT(ShowMainPageCommand.INSTANCE);

    private final Command command;
    private final List<Role> allowedRoles;

    AppCommand(Command command, Role... roles) {
        this.command = command;
        this.allowedRoles = roles != null && roles.length > 0 ? Arrays.asList(roles) : Role.valuesAsList();
    }

    public Command getCommand() {
        return command;
    }

    public List<Role> getAllowedRoles() {
        return allowedRoles;
    }

    public static AppCommand of(String name) {
        for (AppCommand command : values()) {
            if (command.name().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return DEFAULT;
    }
}
