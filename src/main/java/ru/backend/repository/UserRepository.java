package ru.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ru.backend.entity.User;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUserById(Long id);

    User save(User user);

    @Modifying
    @Query(value = "insert into public.subscriptions (subscriber_id, speaker_id) values (:id_subscriber, :id_speaker)", nativeQuery = true)
    void subscribe(@Param("id_subscriber") Long id_subscriber, @Param("id_speaker") Long id_speaker);

    @Modifying
    @Query(value = "update public.user set subscribers = subscribers + 1 where id = :id", nativeQuery = true)
    void updateSubscribers(@Param("id") Long id);

    @Modifying
    @Query(value = "update public.user set subscriptions = subscriptions + 1 where id = :id", nativeQuery = true)
    void updateSubscriptions(@Param("id") Long id);

    @Modifying
    @Query(value = "update public.user set picture = :picture where id = :id", nativeQuery = true)
    void updatePicture(@Param("id") Long id, @Param("picture") String picture);
}
