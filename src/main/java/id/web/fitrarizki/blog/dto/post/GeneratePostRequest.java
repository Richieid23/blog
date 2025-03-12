package id.web.fitrarizki.blog.dto.post;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeneratePostRequest {
    @NotNull
    private String title;
    @NotNull
    private Integer length;
}
