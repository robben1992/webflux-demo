package org.ly.unittest.annotation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.ly.unittest.extension.AwsS3Extension;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@ExtendWith(AwsS3Extension.class)
public @interface AwsS3Test {

}