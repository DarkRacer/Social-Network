package ru.backend.service;


import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.backend.dto.UserDTO;
import ru.backend.mapper.UserMapper;
import ru.backend.entity.User;
import ru.backend.repository.UserRepository;

import javax.persistence.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Value("${upload.patch}")
    private String uploadPatch;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void updatePicture(@Param("id") Long id, MultipartFile multipartFile) throws IOException {
        String fileFormat = "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        UUID uuid = UUID.randomUUID();
        String UUIDString = uuid.toString() + fileFormat;
        String patchName = uploadPatch + UUIDString;

        if (Objects.equals(fileFormat, ".png") ||
                Objects.equals(fileFormat, ".jpg") ||
                Objects.equals(fileFormat, ".jpeg")) {

            File convFile = new File(patchName);
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();

            userRepository.updatePicture(id, UUIDString);
        }
    }
}
