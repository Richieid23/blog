package id.web.fitrarizki.blog.controller;

import id.web.fitrarizki.blog.dto.post.*;
import id.web.fitrarizki.blog.entity.Post;
import id.web.fitrarizki.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/posts")
@RequiredArgsConstructor
public class PostAdminController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<GetPostResponse>> getPosts(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                          @RequestParam(required = false, defaultValue = "10") Integer limit) {
        GetPostsRequest request = GetPostsRequest.builder()
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return ResponseEntity.ok(postService.getPosts(request));
    }

    @GetMapping("/{slug}")
    public GetPostResponse getPost(@PathVariable String slug) {
        return postService.getPostBySlug(slug);
    }

    @PostMapping
    public CreatePostResponse createPost(@Valid @RequestBody CreatePostRequest request) {
        return postService.createPost(request);
    }

    @PutMapping("/{slug}")
    public GetPostResponse updatePost(@PathVariable String slug, @Valid @RequestBody UpdatePostBySlugRequest request) {
        return postService.updatePost(slug, request);
    }

    @DeleteMapping("/{id}")
    public DeletePostResponse deletePost(@PathVariable Integer id) {
        return postService.deletePost(id);
    }

    @PostMapping("/{id}/publish")
    public GetPostResponse publishPost(@PathVariable Integer id) {
        return postService.publishPost(id);
    }
}
