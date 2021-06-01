package com.epam.jwd.web.command.page;

import com.epam.jwd.web.command.Command;
import com.epam.jwd.web.command.CommandRequest;
import com.epam.jwd.web.command.CommandResponse;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.service.UserService;

import java.util.List;

public enum ShowUsersPage implements Command {
    INSTANCE;

    private static final String USERS_ATTRIBUTE_NAME = "users";

    private static final CommandResponse SHOW_USERS_PAGE_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return "/WEB-INF/jsp/users.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private final UserService service;

    ShowUsersPage() {
        this.service = UserService.simple();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<User> users = service.findAll();
        request.setAttribute(USERS_ATTRIBUTE_NAME, users);
        return SHOW_USERS_PAGE_RESPONSE;
    }
}
