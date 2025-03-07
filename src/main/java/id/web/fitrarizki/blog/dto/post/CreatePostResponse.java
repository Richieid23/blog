package id.web.fitrarizki.blog.dto.post;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostResponse {
    private Integer id;
    private String title;
    private String body;
    private String slug;
    private Long commentsCount;
    private Long publishedAt;
}
