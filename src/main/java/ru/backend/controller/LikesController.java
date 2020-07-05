package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.backend.dto.UserDTO;
import ru.backend.service.LikesServices;

@RestController
@RequestMapping("/publications")
public class LikesController {
    private final LikesServices likesServices;

    @Autowired
    public LikesController(LikesServices likesServices) {
        this.likesServices = likesServices;
    }

    @PostMapping("/{id}/like")
    @ResponseStatus(HttpStatus.OK)
    public void save(@PathVariable Long id, @RequestBody @Param("idU") UserDTO user) {
        likesServices.save(id, user.getId());
    }
}
