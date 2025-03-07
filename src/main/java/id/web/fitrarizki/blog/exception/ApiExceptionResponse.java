package id.web.fitrarizki.blog.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiExceptionResponse {
    List<String> errorMessages;
}
