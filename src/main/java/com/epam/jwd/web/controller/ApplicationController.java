package com.epam.jwd.web.controller;

import com.epam.jwd.web.command.Command;
import com.epam.jwd.web.command.CommandRequest;
import com.epam.jwd.web.command.CommandResponse;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.storage.UserStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class ApplicationController extends HttpServlet {

    private static final String COMMAND_PARAM_NAME = "command";

    @Override
    public void init() throws ServletException {
        super.init();
        final UserStorage storage = UserStorage.inMemory();
        storage.save(new User("Alice"));
        storage.save(new User("Bob"));
        storage.save(new User("Martin"));
        storage.save(new User("Kate"));
        storage.save(new User("Lynn"));
        storage.save(new User("Robert"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String commandName = req.getParameter(COMMAND_PARAM_NAME);
        final Command command = Command.withName(commandName);
        final CommandResponse response = command.execute(new CommandRequest() {
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
