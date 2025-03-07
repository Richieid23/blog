package id.web.fitrarizki.blog.dto.category;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryResponse {
    private Integer id;
    private String name;
    private String slug;
}
