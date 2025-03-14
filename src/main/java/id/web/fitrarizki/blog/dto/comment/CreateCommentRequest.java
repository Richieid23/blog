package id.web.fitrarizki.blog.dto.comment;

import jakarta.validation.constraints.Email;
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
public class CreateCommentRequest {

    @NotNull(message = "Name is required")
    @Size(min = 2, max = 255, message = "Name must be between 2 - 100 character")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotNull(message = "Body is required")
    @Size(min = 2, max = 5_000, message = "Body must be between 2 - 5000 character")
    private String body;

    @NotNull
    private String token;

    @NotNull(message = "Post is required")
    private Post post;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        private String title;
        private String slug;
    }
}
