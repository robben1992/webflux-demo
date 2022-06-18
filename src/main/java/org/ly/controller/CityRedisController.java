package org.ly.controller;

import lombok.RequiredArgsConstructor;
import org.ly.domain.City;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/city1/redis2")
@RequiredArgsConstructor
public class CityRedisController {

    private final ReactiveRedisTemplate reactiveRedisTemplate;

    private static final String REDIS_PREFIX_KEY = "web_flux_demo_city_2";

    @GetMapping("/{id}")
    public Mono<City> findById(@PathVariable("id") Long id){
        ReactiveValueOperations<String, City> operations = reactiveRedisTemplate.opsForValue();
        return operations.get(REDIS_PREFIX_KEY + id);
    }

    @PostMapping()
    public Mono<Boolean> save(@RequestBody City city){
        ReactiveValueOperations<String, City> operations = reactiveRedisTemplate.opsForValue();
        return operations.set(REDIS_PREFIX_KEY + city.getId(),city);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable("id") Long id){
        ReactiveValueOperations<String, City> operations = reactiveRedisTemplate.opsForValue();
        return operations.delete(REDIS_PREFIX_KEY + id);
    }
}
