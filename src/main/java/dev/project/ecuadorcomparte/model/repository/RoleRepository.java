package dev.project.ecuadorcomparte.model.repository;

import dev.project.ecuadorcomparte.model.constant.RoleName;
import dev.project.ecuadorcomparte.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepository
        extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}