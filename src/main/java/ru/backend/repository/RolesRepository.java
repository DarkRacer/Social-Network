package ru.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.entity.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
