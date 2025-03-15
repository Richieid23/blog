package id.web.fitrarizki.blog.dto.media;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetMediaRequest {
    private Integer pageNo;
    private Integer limit;
}
