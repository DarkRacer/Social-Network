package ru.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.backend.dto.UserDTO;
import ru.backend.entity.User;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "date_of_birth", target = "date_of_birth")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "subscribers", target = "subscribers")
    @Mapping(source = "subscriptions", target = "subscriptions")
    @Mapping(source = "picture", target = "picture")
    UserDTO from(User user);
}
