package id.web.fitrarizki.blog.service;

import id.web.fitrarizki.blog.dto.category.*;
import id.web.fitrarizki.blog.entity.Category;
import id.web.fitrarizki.blog.exception.ApiException;
import id.web.fitrarizki.blog.mapper.CategoryMapper;
import id.web.fitrarizki.blog.repository.CategoryRepository;
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
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    public List<GetCategoryResponse> getCategories(GetCategoriesRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getLimit());

        return categoryRepository.findAll(pageable).getContent().stream().map(CategoryMapper.INSTANCE::mapToGetCategoryResponse).collect(Collectors.toList());
    }

    public List<GetCategoryResponse> getCategories(GetCategoriesRequest request, boolean isDeleted) {
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getLimit());

        return categoryRepository.findByIsDeleted(isDeleted, pageable).getContent().stream().map(CategoryMapper.INSTANCE::mapToGetCategoryResponse).collect(Collectors.toList());
    }

    public GetCategoryResponse getCategoryById(Integer id) {
        return CategoryMapper.INSTANCE.mapToGetCategoryResponse(categoryRepository.findById(id).orElseThrow(() -> new ApiException("Category not found", HttpStatus.NOT_FOUND)));
    }

    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = CategoryMapper.INSTANCE.mapFromCreateCategoryRequest(request);
        category.setCreatedAt(Instant.now().getEpochSecond());

        return CategoryMapper.INSTANCE.mapToCreateCategoryResponse(categoryRepository.save(category));
    }

    public UpdateCategoryResponse updateCategory(UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(request.getId()).orElseThrow(() -> new ApiException("Category not found", HttpStatus.NOT_FOUND));
        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setUpdatedAt(Instant.now().getEpochSecond());
        category.setUpdatedAt(Instant.now().getEpochSecond());

        return CategoryMapper.INSTANCE.mapToUpdateCategoryResponse(categoryRepository.save(category));
    }

    public DeleteCategoryResponse deleteById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ApiException("Category not found", HttpStatus.NOT_FOUND));
        Long numberOfPost = postRepository.countByCategory((category));

        if (numberOfPost > 0) {
            throw new ApiException("Post is still exists under this category", HttpStatus.CONFLICT);
        }
        category.setIsDeleted(true);

        return DeleteCategoryResponse.builder().id(category.getId()).build();
    }
}
