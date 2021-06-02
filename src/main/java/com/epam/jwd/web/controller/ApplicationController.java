package com.epam.jwd.web.controller;

import com.epam.jwd.web.command.Command;
import com.epam.jwd.web.command.CommandRequest;
import com.epam.jwd.web.command.CommandResponse;
import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/controller")
public class ApplicationController extends HttpServlet {

    public static final String COMMAND_PARAM_NAME = "command";

    @Override
    public void init() throws ServletException {
        super.init();
        final UserService storage = UserService.simple();
        storage.create(new User("Alice", "Alice"));
        storage.create(new User("Bob", "Bob"));
        storage.create(new User("Martin", "Martin"));
        storage.create(new User("Kate", "Kate"));
        storage.create(new User("Lynn", "Lynn"));
        storage.create(new User("Robert", "Robert"));
        storage.create(new User("admin", "password", Role.ADMIN));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String commandName = req.getParameter(COMMAND_PARAM_NAME);
        final Command command = Command.withName(commandName);
        final CommandResponse response = command.execute(new CommandRequest() {
            @Override
            public HttpSession createSession() {
                return req.getSession(true);
            }

            @Override
            public Optional<HttpSession> getCurrentSession() {
                return Optional.ofNullable(req.getSession(false));
            }

            @Override
            public void invalidateCurrentSession() {
                final HttpSession session = req.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
            }

            @Override
            public String getParameter(String name) {
                return req.getParameter(name);
            }

            @Override
            public void setAttribute(String name, Object value) {
                req.setAttribute(name, value);
            }
        });
        if (response.isRedirect()) {
            resp.sendRedirect(response.getPath());
        } else {
            final RequestDispatcher dispatcher = req.getRequestDispatcher(response.getPath());
            dispatcher.forward(req, resp);
        }
//        try {
//        } catch (IOException | ServletException e) {
//            //todo: 500
//        }
    }

}
