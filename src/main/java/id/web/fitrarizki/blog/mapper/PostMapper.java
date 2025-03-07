package id.web.fitrarizki.blog.mapper;

import id.web.fitrarizki.blog.dto.post.CreatePostRequest;
import id.web.fitrarizki.blog.dto.post.CreatePostResponse;
import id.web.fitrarizki.blog.dto.post.GetPostResponse;
import id.web.fitrarizki.blog.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now().getEpochSecond())")
    Post mapFromCreatePostResponse(CreatePostRequest request);
    CreatePostResponse mapToCreatePostResponse(Post post);
    GetPostResponse mapToGetPostResponse(Post post);
}
