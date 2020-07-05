package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.backend.dto.UserDTO;
import ru.backend.entity.User;
import ru.backend.service.UserService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @PostMapping("/{id_speaker}/subscribe")
    @ResponseStatus(HttpStatus.OK)
    public void subscribe(@RequestBody @Param("id_subscriber") User user, @PathVariable @Param("id_speaker") Long id_speaker) {
        userService.subscribe(user.getId(), id_speaker);
    }

    @PostMapping("/{id}/uploadImage")
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePicture(@PathVariable @Param("id") Long id, @RequestParam MultipartFile file) throws IOException {
        userService.updatePicture(id, file);
    }
}
