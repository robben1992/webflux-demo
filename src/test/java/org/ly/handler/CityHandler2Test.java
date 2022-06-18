package org.ly.handler;


import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.ly.WebFluxDemoApplication;
import org.ly.config.MongoConfig;
import org.ly.config.RedisConfig;
import org.ly.domain.City2;
import org.ly.repository.CityRepository2;
import org.ly.unittest.extension.MongoDBExtension;
import org.ly.unittest.extension.RedisExtension;
import org.ly.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ContextConfiguration(classes = {
        MongoConfig.class,
        CityHandler2.class,
        RedisConfig.class,
        WebFluxDemoApplication.class
})
@ExtendWith({SpringExtension.class, MongoDBExtension.class, RedisExtension.class})
class CityHandler2Test {

    private final Duration defaultBlockDuration = Duration.ofSeconds(2);

    @Autowired
    private CityHandler2 cityHandler;
    @Autowired
    private CityRepository2 cityRepository;

    private List<City2> city2s;

    @SneakyThrows
    @BeforeEach
    void init(){
        File collectionMetadataFile = new ClassPathResource("city/city2.json").getFile();
        city2s = JsonUtil.toListByFile(collectionMetadataFile, City2.class);
    }

    @AfterEach
    void clear(){
        cityRepository.deleteAll(city2s).block(defaultBlockDuration);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'1', 12, 南昌市1",
            "'4', 12, 井冈山市1",
            "'8', 13, 深圳市1"
    })
    void findByIdTest(String id, Long provinceId, String cityName){
        cityRepository.saveAll(city2s).blockLast(defaultBlockDuration);
        StepVerifier.create(cityHandler.findById(id))
                .expectNextMatches(city -> Objects.equals(city.getProvinceId(), provinceId) &&
                        city.getCityName().equals(cityName))
                .verifyComplete();
    }

    @SneakyThrows
    @ParameterizedTest
    @CsvSource(value = {
            "南昌市1, '1', 12",
            "井冈山市1, '4', 12",
            "深圳市1, '8', 13"
    })
    void findByNameTest(String cityName,String id, Long provinceId){
        cityRepository.saveAll(city2s).blockLast(defaultBlockDuration);
        StepVerifier.create(cityHandler.findByName(cityName))
                .assertNext(city -> {
                    assertNotNull(city);
                    assertEquals(id, city.getId());
                    assertEquals(provinceId, city.getProvinceId());
                })
                .verifyComplete();
    }
}
