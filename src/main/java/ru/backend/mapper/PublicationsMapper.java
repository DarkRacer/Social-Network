package ru.backend.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.backend.dto.PublicationsDTO;
import ru.backend.entity.Publications;

@Mapper
public interface PublicationsMapper {
    PublicationsMapper PUBLICATIONS_MAPPER = Mappers.getMapper(PublicationsMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "creation_date", target = "creation_date")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "likes", target = "likes")
    PublicationsDTO from(Publications publications);
}
