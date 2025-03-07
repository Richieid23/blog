package id.web.fitrarizki.blog.dto.category;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoriesRequest {
    private Integer pageNo;
    private Integer limit;
}
