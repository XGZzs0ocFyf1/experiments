package ru.xgzzs0ocfyf1.experiments.security;

import ru.xgzzs0ocfyf1.experiments.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    User register(User user);
    List<User> getAll();
    User findByUserName(String username);
    User findById(Long id);
    void deleteById(Long id);
}
