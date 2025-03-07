package id.web.fitrarizki.blog.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ApiException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
