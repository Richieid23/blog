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
@RequestMapping("/api/v1/public/posts")
@RequiredArgsConstructor
public class PostPublicController {
    private final PostService postService;

    @GetMapping("/{slug}")
    public ResponseEntity<GetPostResponse> getPostBySlug(@Valid @PathVariable String slug) {
        return ResponseEntity.ok(postService.getPostBySlug(slug));
    }

    @GetMapping
    public ResponseEntity<List<GetPostResponse>> getPosts(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                          @RequestParam(required = false, defaultValue = "10") Integer limit) {
        GetPostsRequest request = GetPostsRequest.builder()
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return ResponseEntity.ok(postService.getPosts(request, false));
    }
}
