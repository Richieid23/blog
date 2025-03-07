package id.web.fitrarizki.blog.dto.category;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryResponse {
    private Integer id;
    private String name;
    private String slug;
}
