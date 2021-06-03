package com.epam.jwd.web.listener;

import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationLifecycleListener implements ServletContextListener {

    private final UserService service;

    public ApplicationLifecycleListener() {
        service = UserService.simple();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        service.create(new User("Alice", "Alice"));
        service.create(new User("Bob", "Bob"));
        service.create(new User("Martin", "Martin"));
        service.create(new User("Kate", "Kate"));
        service.create(new User("Lynn", "Lynn"));
        service.create(new User("Robert", "Robert"));
        service.create(new User("admin", "password", Role.ADMIN));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        service.clear();
    }
}
