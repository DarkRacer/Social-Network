package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.backend.entity.Likes;
import ru.backend.entity.Publications;
import ru.backend.service.LikesServices;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    private LikesServices likesServices;

    @GetMapping("/publications/{id}")
    public List<Likes> findLikesByPublication(@PathVariable Publications id){
        return likesServices.findLikesByPublication(id);
    }
}
