package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.entity.Publications;
import ru.backend.entity.User;
import ru.backend.repository.PublicationsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
public class PublicationsServices {
    @Autowired
    private final PublicationsRepository publicationsRepository;

    @PersistenceContext
    private EntityManager em;

    public PublicationsServices(PublicationsRepository publicationsRepository) {
        this.publicationsRepository = publicationsRepository;
    }

    public List<Publications> findAll() {
        return publicationsRepository.findAll();
    }

    public List<Publications> findPublicationsById(Long id) {
        return publicationsRepository.findPublicationsById(id);
    }

    public List<Publications> findPublicationsByUser(User user) {
        return publicationsRepository.findPublicationsByUser(user);
    }

    public List<Publications> findPublicationsTop() {
        return publicationsRepository.findPublicationsTop();
    }

    @Transactional
    public Publications save(Publications publications) {
        if (publications.getId() == null) {
            em.persist(publications);
        } else {
            em.merge(publications);
        }
        return publications;
    }

    public void deleteById (Long id){
        publicationsRepository.deleteById(id);
    }
}
