package ru.web.dao;

import ru.web.models.User;

import java.util.List;


public interface UserDao {

    User findUserByUsername(String name);

    List<User> getAllUsers();

    void addUser(User user);

    public User findOne( long id );

    User update(User user);

    void delete(User user);

    void deleteById(long id);
}
