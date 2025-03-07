package id.web.fitrarizki.blog.mapper;

import id.web.fitrarizki.blog.dto.comment.CreateCommentRequest;
import id.web.fitrarizki.blog.dto.comment.CreateCommentResponse;
import id.web.fitrarizki.blog.dto.comment.GetCommentResponse;
import id.web.fitrarizki.blog.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment mapFromCreateCommentRequest(CreateCommentRequest request);
    CreateCommentResponse mapToCreateCommentResponse(Comment comment);
    GetCommentResponse mapToGetCommentResponse(Comment comment);
}
