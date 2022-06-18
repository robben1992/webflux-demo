package org.ly.unittest.annotation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.ly.unittest.extension.MongoDBExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@AutoConfigureDataMongo
@ExtendWith(MongoDBExtension.class)
public @interface MongoTest {

}