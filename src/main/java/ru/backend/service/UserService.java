package ru.backend.service;


import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException.UnprocessableEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.backend.dto.UserDTO;
import ru.backend.entity.Role;
import ru.backend.fileFormat.ImageFormat;
import ru.backend.mapper.UserMapper;
import ru.backend.entity.User;
import ru.backend.repository.RolesRepository;
import ru.backend.repository.UserRepository;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;

    @Value("${upload.patch}")
    private String uploadPatch;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
    }

    public List<UserDTO> findUserById(Long id) {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = userRepository.findUserById(id);

        for (User user : userList)
            userDTOList.add(UserMapper.USER_MAPPER.from(user));

        return userDTOList;
    }

    @Transactional
    public void save(User user) {
        Role role = rolesRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    @Transactional
    public void subscribe(@Param("id_subscriber") Long id_subscriber, @Param("id_speaker") Long id_speaker) {
        userRepository.subscribe(id_subscriber, id_speaker);
        userRepository.updateSubscribers(id_speaker);
        userRepository.updateSubscriptions(id_subscriber);
    }

    @Transactional
    public void updatePicture(@Param("id") Long id, MultipartFile multipartFile) throws IOException, UnprocessableEntity {
        String fileFormat = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String UUIDString = "";

        for (ImageFormat format : ImageFormat.values()) {
            if (format.getFormat().equals(fileFormat)) {
                UUID uuid = UUID.randomUUID();
                UUIDString = uuid.toString() + "." + fileFormat;
            }
        }

        if (UUIDString != "") {
            String patchName = uploadPatch + UUIDString;
            File convFile = new File(patchName);
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();

            userRepository.updatePicture(id, UUIDString);
        } else {
            throw new ru.backend.exception.UnprocessableEntity("Неверный формат файла");
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
