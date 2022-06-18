package org.ly.unittest.annotation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.ly.unittest.extension.MongoDBExtension;
import org.ly.unittest.extension.RedisExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest
@ExtendWith({RedisExtension.class, MongoDBExtension.class})
public @interface SpringWebContextTest {

    @AliasFor(annotation = SpringBootTest.class, attribute = "webEnvironment")
    SpringBootTest.WebEnvironment webEnvironment() default SpringBootTest.WebEnvironment.RANDOM_PORT;
}