package ru.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.backend.entity.Subscriptions;
import ru.backend.entity.User;

@Component
public interface SubscriptionsRepository extends JpaRepository<Subscriptions, User> {
}