package id.web.fitrarizki.blog.dto.comment;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentResponse {
    private String name;
    private String email;
    private String body;
    private Post post;
    private Long createdAt;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        private String title;
        private String slug;
    }
}
