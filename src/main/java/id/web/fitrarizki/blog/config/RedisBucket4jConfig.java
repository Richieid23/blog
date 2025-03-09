package id.web.fitrarizki.blog.config;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import lombok.RequiredArgsConstructor;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

@Configuration
@RequiredArgsConstructor
public class RedisBucket4jConfig {

    private final RedisPropertiesConfig redisProp;
    private final String cacheName = "bucket4j-rate-limit";

    @Bean
    public Config redissonConfig() {
        Config config = new Config();
        String addr = "%s:%s".formatted(redisProp.getHost(), redisProp.getPort());
        addr = "redis://%s".formatted(addr);
        config.useSingleServer().setAddress(addr);
        return config;
    }

    @Bean
    public CacheManager bucket4jCacheManager(Config redissonConfig) {
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();
        cacheManager.createCache(cacheName, RedissonConfiguration.fromConfig(redissonConfig));
        return cacheManager;
    }

    @Bean
    public ProxyManager<String> proxyManager(CacheManager cacheManager) {
        return new JCacheProxyManager<>(cacheManager.getCache(cacheName));
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
