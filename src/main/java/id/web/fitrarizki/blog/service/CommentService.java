package id.web.fitrarizki.blog.service;

import id.web.fitrarizki.blog.dto.comment.CreateCommentRequest;
import id.web.fitrarizki.blog.dto.comment.CreateCommentResponse;
import id.web.fitrarizki.blog.dto.comment.GetCommentResponse;
import id.web.fitrarizki.blog.entity.Comment;
import id.web.fitrarizki.blog.entity.Post;
import id.web.fitrarizki.blog.exception.ApiException;
import id.web.fitrarizki.blog.mapper.CommentMapper;
import id.web.fitrarizki.blog.repository.CommentRepository;
import id.web.fitrarizki.blog.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public Iterable<GetCommentResponse> getComments(String postSlug, Integer page, Integer limit) {
        Post post = postRepository.findBySlugAndIsDeleted(postSlug, false).orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, limit);
        return commentRepository.findByPost(post, pageRequest).getContent().stream().map(CommentMapper.INSTANCE::mapToGetCommentResponse).collect(Collectors.toList());
    }

    public GetCommentResponse getComment(Integer id) {
        return CommentMapper.INSTANCE.mapToGetCommentResponse(commentRepository.findById(id).orElseThrow(() -> new ApiException("Comment not found", HttpStatus.NOT_FOUND)));
    }

    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest request) {
        Post post = postRepository.findBySlugAndIsDeleted(request.getPost().getSlug(), false).orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        Comment comment = CommentMapper.INSTANCE.mapFromCreateCommentRequest(request);
        comment.setPost(post);
        comment.setCreatedAt(Instant.now().getEpochSecond());

        comment = commentRepository.save(comment);

        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        return CommentMapper.INSTANCE.mapToCreateCommentResponse(comment);
    }
}
