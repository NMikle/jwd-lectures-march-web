package com.epam.jwd.web.command;

import com.epam.jwd.web.model.User;
import com.epam.jwd.web.storage.UserStorage;

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

    private final UserStorage storage;

    ShowUsersPage() {
        this.storage = UserStorage.inMemory();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<User> users = storage.findAll();
        request.setAttribute(USERS_ATTRIBUTE_NAME, users);
        return SHOW_USERS_PAGE_RESPONSE;
    }
}
