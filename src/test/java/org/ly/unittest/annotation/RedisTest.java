package org.ly.unittest.annotation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.ly.unittest.extension.RedisExtension;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;

import java.lang.annotation.*;

/**
 * There are some bugs with {@link AutoConfigureDataRedis} in the version 2.4.3,so I have to import redis config manually
 * Can remove {@link ImportAutoConfiguration} when we upgrade spring boot
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@AutoConfigureDataRedis
@ImportAutoConfiguration({RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class})
@ExtendWith(RedisExtension.class)
public @interface RedisTest {

}