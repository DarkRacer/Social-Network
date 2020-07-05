package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.dto.PublicationsDTO;
import ru.backend.entity.Publications;
import ru.backend.entity.User;
import ru.backend.mapper.PublicationsMapper;
import ru.backend.repository.PublicationsRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Component
public class PublicationsServices {
    private final PublicationsRepository publicationsRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public PublicationsServices(PublicationsRepository publicationsRepository) {
        this.publicationsRepository = publicationsRepository;
    }

    public List<PublicationsDTO> findAll() {
        List<PublicationsDTO> publicationsDTOList = new ArrayList<>();
        List<Publications> publicationsList = publicationsRepository.findAll();

        for (Publications publications : publicationsList)
            publicationsDTOList.add(PublicationsMapper.PUBLICATIONS_MAPPER.from(publications));

        return publicationsDTOList;
    }

    public List<PublicationsDTO> findPublicationsById(Long id) {
        List<PublicationsDTO> publicationsDTOList = new ArrayList<>();
        List<Publications> publicationsList = publicationsRepository.findPublicationsById(id);

        for (Publications publications : publicationsList)
            publicationsDTOList.add(PublicationsMapper.PUBLICATIONS_MAPPER.from(publications));

        return publicationsDTOList;
    }

    public List<PublicationsDTO> findPublicationsByUser(User user) {
        List<PublicationsDTO> publicationsDTOList = new ArrayList<>();
        List<Publications> publicationsList = publicationsRepository.findPublicationsByUser(user);

        for (Publications publications : publicationsList)
            publicationsDTOList.add(PublicationsMapper.PUBLICATIONS_MAPPER.from(publications));

        return publicationsDTOList;
    }

    public List<PublicationsDTO> findPublicationsTop() {
        List<PublicationsDTO> publicationsDTOList = new ArrayList<>();
        List<Publications> publicationsList = publicationsRepository.findPublicationsTop();

        for (Publications publications : publicationsList)
            publicationsDTOList.add(PublicationsMapper.PUBLICATIONS_MAPPER.from(publications));

        return publicationsDTOList;
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
