package org.ly.unittest.annotation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.ly.unittest.extension.MockServerExtension;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@AutoConfigureWebClient
@ExtendWith(MockServerExtension.class)
public @interface MockServerTest {

}