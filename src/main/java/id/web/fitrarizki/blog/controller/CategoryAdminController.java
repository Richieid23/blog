package id.web.fitrarizki.blog.controller;

import id.web.fitrarizki.blog.dto.category.*;
import id.web.fitrarizki.blog.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<GetCategoryResponse>> getCategories(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                                   @RequestParam(required = false, defaultValue = "10") Integer limit) {
        GetCategoriesRequest request = GetCategoriesRequest.builder()
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return ResponseEntity.ok(categoryService.getCategories(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryResponse> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<CreateCategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryService.createCategory(request));
    }

    @PutMapping
    public ResponseEntity<UpdateCategoryResponse> updateCategory(@Valid @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryService.updateCategory(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteCategoryResponse> deleteCategoryById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.deleteById(id));
    }
}
