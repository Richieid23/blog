package id.web.fitrarizki.blog.dto.post;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostsRequest {
    private Integer pageNo;
    private Integer limit;
}
