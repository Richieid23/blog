package id.web.fitrarizki.blog.mapper;

import id.web.fitrarizki.blog.dto.category.CreateCategoryRequest;
import id.web.fitrarizki.blog.dto.category.CreateCategoryResponse;
import id.web.fitrarizki.blog.dto.category.GetCategoryResponse;
import id.web.fitrarizki.blog.dto.category.UpdateCategoryResponse;
import id.web.fitrarizki.blog.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category mapFromCreateCategoryRequest(CreateCategoryRequest request);
    GetCategoryResponse mapToGetCategoryResponse(Category category);
    CreateCategoryResponse mapToCreateCategoryResponse(Category category);
    UpdateCategoryResponse mapToUpdateCategoryResponse(Category category);
}
