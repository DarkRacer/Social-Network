package ru.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ru.backend.entity.Publications;
import ru.backend.entity.User;
import java.util.List;

@Component
public interface PublicationsRepository extends JpaRepository<Publications, Long> {
    List<Publications> findAll();

    List<Publications> findPublicationsById(Long id);

    List<Publications> findPublicationsByUser(User user);

    @Query(value = "select * from public.\"publications\" order by likes desc limit 5", nativeQuery = true)
    List<Publications> findPublicationsTop();

    Publications save (Publications publications);

    void deleteById (Long id);
}
