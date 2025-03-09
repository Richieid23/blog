package id.web.fitrarizki.blog.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis")
@PropertySource("classpath:redis.properties")
@Setter
@Getter
public class RedisPropertiesConfig {
    private String host;
    private String password;
    private String port;
}
