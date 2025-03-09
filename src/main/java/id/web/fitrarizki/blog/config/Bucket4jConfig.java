package id.web.fitrarizki.blog.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class Bucket4jConfig {

    private final ProxyManager<String> proxyManager;

    public Bucket loginBucket(String username) {
        Refill refill = Refill.intervally(3, Duration.ofMinutes(1));
        Bandwidth bandwidth = Bandwidth.classic(3, refill);
        BucketConfiguration configuration = BucketConfiguration.builder()
                .addLimit(bandwidth).build();
        return proxyManager.builder().build(username, () -> configuration);
    }

    public Bucket loginBucket() {
        Refill refill = Refill.intervally(3, Duration.ofMinutes(1));
        Bandwidth bandwidth = Bandwidth.classic(3, refill);
        return Bucket.builder().addLimit(bandwidth).build();
    }
}
