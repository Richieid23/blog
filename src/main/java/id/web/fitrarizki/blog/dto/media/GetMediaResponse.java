package id.web.fitrarizki.blog.dto.media;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetMediaResponse {
    private Integer id;
    private String path;
    private String name;
}
