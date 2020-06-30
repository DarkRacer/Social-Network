package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.backend.entity.Publications;
import ru.backend.entity.User;
import ru.backend.service.PublicationsServices;

import java.util.List;

@RestController
@RequestMapping("/publications")
public class PublicationsController {
    @Autowired
    private PublicationsServices publicationsServices;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Publications> findAll(){
        return publicationsServices.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Publications> findPublicationsById(@PathVariable Long id){
        return publicationsServices.findPublicationsById(id);
    }

    @GetMapping("/user/{user}")
    @ResponseStatus(HttpStatus.OK)
    public List<Publications> findPublicationsByUser(@PathVariable User user){
        return publicationsServices.findPublicationsByUser(user);
    }

    @GetMapping("/top")
    @ResponseStatus(HttpStatus.OK)
    public List<Publications> findPublicationsTop(){
        return publicationsServices.findPublicationsTop();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Publications save(@RequestBody Publications publications){
        return publicationsServices.save(publications);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        publicationsServices.deleteById(id);
    }
}
