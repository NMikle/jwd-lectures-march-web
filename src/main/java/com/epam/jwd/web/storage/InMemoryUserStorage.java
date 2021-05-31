package com.epam.jwd.web.storage;

import com.epam.jwd.web.model.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public enum InMemoryUserStorage implements UserStorage {
    INSTANCE;

    private final Map<Long, User> content;
    private final AtomicLong userAmount;

    InMemoryUserStorage() {
        this.content = new ConcurrentHashMap<>();
        this.userAmount = new AtomicLong(0);
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(content.values());
    }

    @Override
    public User save(User user) {
        final long id = userAmount.incrementAndGet();
        return content.put(id, new User(id, user.getName()));
    }
}
