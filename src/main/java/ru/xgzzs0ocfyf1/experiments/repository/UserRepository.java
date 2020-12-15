package ru.xgzzs0ocfyf1.experiments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.xgzzs0ocfyf1.experiments.model.User;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUserName(String username);
}
