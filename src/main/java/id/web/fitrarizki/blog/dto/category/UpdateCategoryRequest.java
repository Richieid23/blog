package id.web.fitrarizki.blog.dto.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequest {
    @NotNull
    private Integer id;

    @Size(min = 2, max = 100)
    @NotNull
    private String name;

    @Size(min = 2, max = 100)
    @NotNull
    private String slug;

}
