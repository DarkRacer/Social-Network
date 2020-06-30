package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.entity.User;
import ru.backend.repository.UserRepository;
import javax.persistence.*;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUserById(Long id){
        return userRepository.findUserById(id);
    }

    @Transactional
    public User save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    @Transactional
    public void subscribe(@Param("id_subscriber") Long id_subscriber, @Param("id_speaker") Long id_speaker) {
        userRepository.subscribe(id_subscriber, id_speaker);
        userRepository.updateSubscribers(id_speaker);
        userRepository.updateSubscriptions(id_subscriber);
    }
}
