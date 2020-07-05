package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.entity.Likes;
import ru.backend.repository.LikesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class LikesServices {
    private final LikesRepository likesRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public LikesServices(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    @Transactional
    public void save(@Param("idP") Long id, @Param("idU") Long id_user) {
        List<Likes> likesList = likesRepository.check(id, id_user);
        if (likesList.isEmpty()) {
            likesRepository.save(id, id_user);
            likesRepository.updatePublication(id);
        } else {
            likesRepository.updatePublicationDeleteLike(id);
            likesRepository.deleteLike(id, id_user);
        }

    }
}
