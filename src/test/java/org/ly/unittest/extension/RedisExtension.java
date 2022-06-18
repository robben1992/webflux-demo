package org.ly.unittest.extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;

public class RedisExtension implements BeforeAllCallback, AfterEachCallback {

    private static final GenericContainer redisContainer =
            new GenericContainer("redis:6.2").withExposedPorts(6379);

    @Override
    public void beforeAll(ExtensionContext context) {
        redisContainer.start();
        System.setProperty("spring.redis.host", redisContainer.getHost());
        System.setProperty("spring.redis.port", redisContainer.getFirstMappedPort().toString());
        System.setProperty("spring.redis.url",
                "redis://" + redisContainer.getHost() + ":" + redisContainer.getFirstMappedPort().toString());
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        redisContainer.execInContainer("redis-cli", "flushall");
    }
}