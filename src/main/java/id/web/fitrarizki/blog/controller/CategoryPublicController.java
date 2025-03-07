package id.web.fitrarizki.blog.controller;

import id.web.fitrarizki.blog.dto.category.GetCategoriesRequest;
import id.web.fitrarizki.blog.dto.category.GetCategoryResponse;
import id.web.fitrarizki.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public/categories")
public class CategoryPublicController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<GetCategoryResponse>> getCategories(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                                   @RequestParam(required = false, defaultValue = "10") Integer limit) {
        GetCategoriesRequest request = GetCategoriesRequest.builder()
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return ResponseEntity.ok(categoryService.getCategories(request, false));
    }
}
