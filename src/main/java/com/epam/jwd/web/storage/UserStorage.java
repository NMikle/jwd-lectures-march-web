package com.epam.jwd.web.storage;

import com.epam.jwd.web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {

    List<User> findAll();

    User save(User user);

    Optional<User> findByName(String name);

    void clear();

    static UserStorage inMemory() {
        return InMemoryUserStorage.INSTANCE;
    }

}
