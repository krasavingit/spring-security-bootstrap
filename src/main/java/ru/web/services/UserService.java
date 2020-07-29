package ru.web.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.web.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    void addUser(User user);

    public User findOne( long id );

    void deleteById(Long id);

    public void edit(User user);
}
