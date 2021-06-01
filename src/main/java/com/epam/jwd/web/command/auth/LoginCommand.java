package com.epam.jwd.web.command.auth;

import com.epam.jwd.web.command.Command;
import com.epam.jwd.web.command.CommandRequest;
import com.epam.jwd.web.command.CommandResponse;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.service.UserService;

import javax.servlet.http.HttpSession;

public enum LoginCommand implements Command {
    INSTANCE;

    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PASSWORD_PARAM = "password";
    private static final String ERROR_ATTRIB_NAME = "error";
    private static final String INVALID_CREDENTIALS_MSG = "Wrong login or password";
    private static final String USER_SESSION_ATTRIB_NAME = "user";

    private static final CommandResponse LOGIN_ERROR_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return "/WEB-INF/jsp/login.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    public static final CommandResponse LOGIN_SUCCESS_RESPONSE = new CommandResponse() {
        @Override
        public String getPath() {
            return "/index.jsp";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };
    private final UserService service;

    LoginCommand() {
        service = UserService.simple();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String login = request.getParameter(LOGIN_PARAM_NAME);
        final String password = request.getParameter(PASSWORD_PASSWORD_PARAM);
        final User user = new User(login, password);
        if (!service.canLogIn(user)) {
            request.setAttribute(ERROR_ATTRIB_NAME, INVALID_CREDENTIALS_MSG);
            return LOGIN_ERROR_RESPONSE;
        }
        request.getCurrentSession().ifPresent(HttpSession::invalidate);
        final HttpSession session = request.createSession();
        session.setAttribute(USER_SESSION_ATTRIB_NAME, login);
        return LOGIN_SUCCESS_RESPONSE;
    }
}
