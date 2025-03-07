package id.web.fitrarizki.blog.dto.post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    @NotNull(message = "Title cannot be empty")
    @Size(min = 2, message = "Title minimal 2 character")
    private String title;

    @NotNull(message = "body cannot be empty")
    @Size(min = 10, message = "Body minimal 10 character")
    private String body;

    @NotNull(message = "Slug cannot be empty")
    @Size(min = 2, message = "Slug minimal 2 character")
    private String slug;
}
