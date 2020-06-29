package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.entity.Likes;
import ru.backend.entity.Publications;
import ru.backend.repository.LikesRepository;

import java.util.List;

@Service
public class LikesServices {
    @Autowired
    private final LikesRepository likesRepository;

    public LikesServices(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    public List<Likes> findLikesByPublication(Publications id){
        return likesRepository.findLikesByPublication(id);
    }
}
