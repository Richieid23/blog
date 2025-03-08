package id.web.fitrarizki.blog.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class CorsFilter implements CorsConfigurationSource {
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(
                Arrays.asList(
                        "http://localhost:8000",
                        "https://blog-fe-dot-blog-project-453009.uc.r.appspot.com",
                        "https://blog.cyberboy.tech",
                        "https://www.blog.cyberboy.tech"
                )
        );

        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setMaxAge(Duration.ofMinutes(5L));
        return config;
    }
}
