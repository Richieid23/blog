package id.web.fitrarizki.blog.service;

import id.web.fitrarizki.blog.dto.post.*;
import id.web.fitrarizki.blog.entity.Category;
import id.web.fitrarizki.blog.entity.Post;
import id.web.fitrarizki.blog.exception.ApiException;
import id.web.fitrarizki.blog.mapper.PostMapper;
import id.web.fitrarizki.blog.repository.CategoryRepository;
import id.web.fitrarizki.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public List<GetPostResponse> getPosts(GetPostsRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getLimit(), Sort.by("createdAt").descending());

        return postRepository.findAll(pageable).getContent().stream().map(PostMapper.INSTANCE::mapToGetPostResponse).collect(Collectors.toList());
    }

    @Cacheable(value = "PostService.getPosts", key = "{#request.pageNo, #isDeleted}")
    public List<GetPostResponse> getPosts(GetPostsRequest request, boolean isDeleted) {
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getLimit(), Sort.by("publishedAt").descending());

        return postRepository.findByIsDeletedAndIsPublished(isDeleted, true, pageable).getContent().stream().map(PostMapper.INSTANCE::mapToGetPostResponse).collect(Collectors.toList());
    }

    @Cacheable(value = "PostService.getPostBySlug", key = "#slug")
    public GetPostResponse getPostBySlug(String slug) {
        return postRepository.findBySlugAndIsDeleted(slug, false).map(PostMapper.INSTANCE::mapToGetPostResponse).orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));
    }

    public CreatePostResponse createPost(CreatePostRequest request) {
        Category category = categoryRepository.findById(request.getCategory().getId()).orElseThrow(() -> new ApiException("Category not found", HttpStatus.NOT_FOUND));

        if (postRepository.findBySlug(request.getSlug()).isPresent()) {
            throw new ApiException("Post slug already exists", HttpStatus.CONFLICT);
        }

        Post post = PostMapper.INSTANCE.mapFromCreatePostResponse(request);
        post.setCommentCount(0L);
        post.setCategory(category);
        return PostMapper.INSTANCE.mapToCreatePostResponse(postRepository.save(post));
    }

    public GetPostResponse updatePost(String slug, UpdatePostBySlugRequest request) {
        Post data = postRepository.findBySlugAndIsDeleted(slug, false).orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));
        if (postRepository.findBySlug(request.getSlug()).isPresent() && !request.getSlug().equals(slug)) {
            throw new ApiException("Post slug already exists", HttpStatus.CONFLICT);
        }

        Category category = categoryRepository.findById(request.getCategory().getId()).orElseThrow(() -> new ApiException("Category not found", HttpStatus.NOT_FOUND));

        data.setTitle(request.getTitle());
        data.setBody(request.getBody());
        data.setSlug(request.getSlug());
        data.setCategory(category);
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
