package org.ly.unittest.annotation;

import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@WebFluxTest
@Import({})
@ActiveProfiles("test")
public @interface SpringSliceTest {

    @AliasFor(annotation = WebFluxTest.class, attribute = "controllers")
    Class<?>[] controllers() default {};

    @AliasFor(annotation = Import.class, attribute = "value")
    Class<?>[] configurations() default {};

    @AliasFor(annotation = Import.class, attribute = "value")
    Class<?>[] value() default {};
}