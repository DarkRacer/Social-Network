package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.repository.LikesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class LikesServices {
    @Autowired
    private final LikesRepository likesRepository;

    @PersistenceContext
    private EntityManager em;

    public LikesServices(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    @Transactional
    public void save(@Param("idP") Long id, @Param("idU") Long id_user) {
        likesRepository.save(id, id_user);
        likesRepository.updatePublication(id);
    }
}
