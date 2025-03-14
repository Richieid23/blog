package id.web.fitrarizki.blog.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@PropertySource("classpath:secret.properties")
@ConfigurationProperties(prefix = "blog")
public class SecretPropertiesConfig {
    private User user;
    private Jwt jwt;
    private OpenApi openApi;
    private Google google;

    @Setter
    @Getter
    public static class User {
        private String username;
        private String password;
    }

    @Setter
    @Getter
    public static class Jwt {
        private String iss;
        private String secretKey;
    }

    @Setter
    @Getter
    public static class OpenApi {
        private String url;
        private String apiKey;
    }

    @Getter
    @Setter
    public static class Google {
        private String projectId;
        private String recaptchaKey;
    }
}
