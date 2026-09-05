package com.bilald.crudsample.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class RateLimitingService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    /**
     * Resolves or creates a rate limit bucket for the given key (typically IP address)
     *
     * @param key unique identifier (IP address)
     * @return Bucket for rate limiting
     */
    public Bucket resolveBucket(String key) {
        return cache.computeIfAbsent(key, k -> {
            log.debug("Creating new rate limit bucket for: {}", k);
            return createNewBucket();
        });
    }

    /**
     * Creates a new bucket with rate limit configuration
     * Allows 10 requests per second per IP
     */
    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(
            10, // capacity: 10 tokens
            Refill.intervally(10, Duration.ofSeconds(1)) // refill 10 tokens every 1 second
        );

        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    /**
     * Clears the cache (useful for testing or manual reset)
     */
    public void clearCache() {
        log.info("Clearing rate limit cache");
        cache.clear();
    }

    /**
     * Gets the current cache size
     */
    public int getCacheSize() {
        return cache.size();
    }
}
