package ru.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ru.backend.entity.Likes;

@Component
public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Modifying
    @Query(value = "insert into public.likes (publication_id, user_id) values (:idP, :idU)", nativeQuery = true)
    void save(@Param("idP") Long idP, @Param("idU") Long idU);

    @Modifying
    @Query(value = "update public.publications set likes = likes + 1 where id = :id", nativeQuery = true)
    void updatePublication(@Param("id") Long id);
}
