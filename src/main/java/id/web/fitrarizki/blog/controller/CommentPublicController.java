package id.web.fitrarizki.blog.controller;

import id.web.fitrarizki.blog.dto.comment.CreateCommentRequest;
import id.web.fitrarizki.blog.dto.comment.CreateCommentResponse;
import id.web.fitrarizki.blog.dto.comment.GetCommentResponse;
import id.web.fitrarizki.blog.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/public/comments")
@RequiredArgsConstructor
public class CommentPublicController {
    private final CommentService commentService;

    @GetMapping
    public Iterable<GetCommentResponse> getComments(@RequestParam String postSlug,
                                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                                    @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return commentService.getComments(postSlug, page, limit);
    }

    @PostMapping
    public CreateCommentResponse createComment(@Valid @RequestBody CreateCommentRequest request) throws IOException {
        return commentService.createComment(request);
    }
}
