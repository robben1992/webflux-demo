package org.ly.repository;

import org.ly.domain.City2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CityRepository2 extends ReactiveMongoRepository<City2, String> {

    Mono<Long> countByCityNameLike(String cityName);

    Flux<City2> findByCityNameLike(String cityName, Pageable page);

    Mono<City2> findByCityName(String cityName);
}
