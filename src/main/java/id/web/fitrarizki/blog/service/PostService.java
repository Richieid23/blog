package id.web.fitrarizki.blog.service;

import id.web.fitrarizki.blog.dto.post.*;
import id.web.fitrarizki.blog.entity.Post;
import id.web.fitrarizki.blog.exception.ApiException;
import id.web.fitrarizki.blog.mapper.PostMapper;
import id.web.fitrarizki.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<GetPostResponse> getPosts(GetPostsRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getLimit());

        return postRepository.findAll(pageable).getContent().stream().map(PostMapper.INSTANCE::mapToGetPostResponse).collect(Collectors.toList());
    }

    public List<GetPostResponse> getPosts(GetPostsRequest request, boolean isDeleted) {
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getLimit());

        return postRepository.findByIsDeletedAndIsPublished(isDeleted, true, pageable).getContent().stream().map(PostMapper.INSTANCE::mapToGetPostResponse).collect(Collectors.toList());
    }

    public GetPostResponse getPostBySlug(String slug) {
        return postRepository.findBySlugAndIsDeleted(slug, false).map(PostMapper.INSTANCE::mapToGetPostResponse).orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));
    }

    public CreatePostResponse createPost(CreatePostRequest request) {
        if (postRepository.findBySlug(request.getSlug()).isPresent()) {
            throw new ApiException("Post slug already exists", HttpStatus.CONFLICT);
        }

        Post post = PostMapper.INSTANCE.mapFromCreatePostResponse(request);
        post.setCommentCount(0L);
        return PostMapper.INSTANCE.mapToCreatePostResponse(postRepository.save(post));
    }

    public GetPostResponse updatePost(String slug, Post post) {
        Post data = postRepository.findBySlugAndIsDeleted(slug, false).orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));
        if (postRepository.findBySlug(post.getSlug()).isPresent() && !post.getSlug().equals(slug)) {
            throw new ApiException("Post slug already exists", HttpStatus.CONFLICT);
        }

        data.setTitle(post.getTitle());
        data.setBody(post.getBody());
        data.setSlug(post.getSlug());
        return PostMapper.INSTANCE.mapToGetPostResponse(postRepository.save(data));
    }

    public DeletePostResponse deletePost(Integer id) {
        Post data = postRepository.findById(id).orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        data.setDeleted(true);
        postRepository.save(data);
        return DeletePostResponse.builder().id(id).build();
    }

    public GetPostResponse publishPost(Integer id) {
        Post data = postRepository.findByIdAndIsDeleted(id, false).orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        data.setPublishedAt(Instant.now().getEpochSecond());
        data.setPublished(true);
        return PostMapper.INSTANCE.mapToGetPostResponse(postRepository.save(data));
    }
}
