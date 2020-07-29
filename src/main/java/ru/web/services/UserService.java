package ru.web.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.web.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    void addUser(User user);

    User findOne(long id );

    void deleteById(Long id);

    void edit(User user);
}
