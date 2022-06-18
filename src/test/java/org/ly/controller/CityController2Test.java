package org.ly.controller;


import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ly.unittest.extension.MongoDBExtension;
import org.ly.unittest.extension.RedisExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith({MongoDBExtension.class, RedisExtension.class})
class CityController2Test {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void countByNameTest(){
        webTestClient.get().uri(uriBuilder -> uriBuilder.path("/city2/name/count")
                        .queryParam("name", "吉安市").build())
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Long.class);
    }
}
