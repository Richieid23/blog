package id.web.fitrarizki.blog.controller;

import id.web.fitrarizki.blog.dto.comment.CreateCommentRequest;
import id.web.fitrarizki.blog.dto.comment.CreateCommentResponse;
import id.web.fitrarizki.blog.dto.comment.GetCommentResponse;
import id.web.fitrarizki.blog.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/comments")
@RequiredArgsConstructor
public class CommentAdminController {
    private final CommentService commentService;

    @GetMapping
    public Iterable<GetCommentResponse> getComments(@RequestParam(required = false) String postSlug,
                                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                                    @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return commentService.getComments(postSlug, page, limit);
    }

    @GetMapping("/{id}")
    public GetCommentResponse getComment(@PathVariable Integer id) {
        return commentService.getComment(id);
    }

    @PostMapping
    public CreateCommentResponse createComment(@Valid @RequestBody CreateCommentRequest request) {
        return commentService.createComment(request);
    }
}
