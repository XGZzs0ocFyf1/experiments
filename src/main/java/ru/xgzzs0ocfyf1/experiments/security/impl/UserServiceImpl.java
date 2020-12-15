package ru.xgzzs0ocfyf1.experiments.security.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.xgzzs0ocfyf1.experiments.model.EntityStatus;
import ru.xgzzs0ocfyf1.experiments.model.Role;
import ru.xgzzs0ocfyf1.experiments.model.User;
import ru.xgzzs0ocfyf1.experiments.repository.RoleRepository;
import ru.xgzzs0ocfyf1.experiments.repository.UserRepository;
import ru.xgzzs0ocfyf1.experiments.security.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(EntityStatus.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("::register: user {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result =  userRepository.findAll();
        log.info("::getAll(): {} user found.", result.size());
        return result;
    }

    @Override
    public User findByUserName(String username) {
        User result = userRepository.findByUserName(username);
        //todo: handle case if we can't find user
        log.info("::findByUsername: user: {} found by username {}", result, username);

        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null){
            log.warn(" :: findById:  no user found by id: {}", id);
            return null;
        }
        log.info("::findByUsername: user: {} found by username {}", result, id);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("::deleteById: user with id {} successfully deleted", id);
        
    }
}
