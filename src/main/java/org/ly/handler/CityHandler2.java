package org.ly.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ly.domain.City2;
import org.ly.repository.CityRepository2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CityHandler2 {

    private static final String REDIS_PREFIX_KEY = "web_flux_demo_city_3";

    private final CityRepository2 cityRepository;
    private final ReactiveRedisTemplate reactiveRedisTemplate;

    public Mono<Long> countByCityNameLike(String cityName){
        return cityRepository.countByCityNameLike(cityName);
    }

    public Flux<City2> findPageByNameLike(String name, Integer page, Integer size){
        return cityRepository.findByCityNameLike(name, PageRequest.of(page,size, Sort.Direction.DESC, City2.Fields.cityName));
    }

    public Mono<City2> findByName(String name){
        return cityRepository.findByCityName(name);
    }

    public Mono<City2> save(City2 city){
        return cityRepository.insert(city);
    }

    public Mono<City2> findById(String id){
        ReactiveValueOperations<String, City2> operations = reactiveRedisTemplate.opsForValue();
        String key = REDIS_PREFIX_KEY + id;

        return operations.get(key)
                .switchIfEmpty(cityRepository.findById(id)
                        .switchIfEmpty(Mono.empty())
                        .flatMap(c -> operations.set(key, c).thenReturn(c)));
    }

    public Flux<City2> findAll(){
        return cityRepository.findAll();
    }

    public Mono<Boolean> modify(City2 city){
        ReactiveValueOperations<String, City2> operations = reactiveRedisTemplate.opsForValue();
        return cityRepository.findById(city.getId())
                .switchIfEmpty(Mono.empty())
                .flatMap(exist -> cityRepository.save(city))
                .flatMap(c -> operations.delete(REDIS_PREFIX_KEY + city.getId()));
    }

    public Mono<Boolean> delete(String id){
        ReactiveValueOperations<String, City2> operations = reactiveRedisTemplate.opsForValue();
        return cityRepository.findById(id)
                .flatMap(exist -> cityRepository.deleteById(id))
                .then(operations.delete(REDIS_PREFIX_KEY + id));
    }
}
