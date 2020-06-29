package ru.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.backend.entity.User;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUserById(Long id);

    User save (User user);
}
