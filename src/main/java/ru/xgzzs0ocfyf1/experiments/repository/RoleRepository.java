package ru.xgzzs0ocfyf1.experiments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.xgzzs0ocfyf1.experiments.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String role_user);
}
