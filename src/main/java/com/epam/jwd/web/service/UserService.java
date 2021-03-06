package com.epam.jwd.web.service;

import com.epam.jwd.web.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    boolean canLogIn(User user);

    User findByLogin(String login);

    List<User> findAll();

    void clear();

    static UserService simple() {
        return SimpleUserService.INSTANCE;
    }

}
