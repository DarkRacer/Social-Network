package ru.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.backend.entity.Subscribers;
import ru.backend.entity.User;

@Component
public interface SubscribersRepository extends JpaRepository<Subscribers, User> {
}
