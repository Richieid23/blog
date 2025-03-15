package id.web.fitrarizki.blog.mapper;

import id.web.fitrarizki.blog.dto.media.CreateMediaResponse;
import id.web.fitrarizki.blog.dto.media.GetMediaResponse;
import id.web.fitrarizki.blog.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MediaMapper {
    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);

    GetMediaResponse mapToGetMediaResponse(Media media);
    CreateMediaResponse mapToCreateMediaResponse(Media media);

}
