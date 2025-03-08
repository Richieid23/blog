package id.web.fitrarizki.blog.dto.post;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostBySlugRequest {
    @Size(min=2, message = "minimal 2 characters")
    @NotNull
    private String title;
    @Size(min=10, message = "minimal 10 characters")
    @NotNull
    private String body;
    @Size(min=2, message = "minimal 2 characters")
    @NotNull
    private String slug;

    @NotNull
    @Valid
    private Category category;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category {
        @NotNull
        private Integer id;
    }
}
