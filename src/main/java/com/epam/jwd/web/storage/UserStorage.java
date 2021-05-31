package com.epam.jwd.web.storage;

import com.epam.jwd.web.model.User;

import java.util.List;

public interface UserStorage {

    List<User> findAll();

    User save(User user);

    static UserStorage inMemory() {
        return InMemoryUserStorage.INSTANCE;
    }

}
