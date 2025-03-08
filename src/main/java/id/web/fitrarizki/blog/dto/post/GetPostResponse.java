package id.web.fitrarizki.blog.dto.post;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostResponse {
    private Integer id;
    private String title;
    private String body;
    private String slug;
    private Long commentCount;
    private Long publishedAt;
    private Category category;
    private boolean published;


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category {
        private Integer id;
        private String name;
        private String slug;
    }
}
