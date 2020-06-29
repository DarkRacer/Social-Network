package ru.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ru.backend.entity.Likes;
import ru.backend.entity.Publications;

import java.util.List;

@Component
public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query(value = "select publication_id, user_id from public.\"likes\" where publication_id = 5", nativeQuery = true)
    List<Likes> findLikesByPublication(Publications id);
}
