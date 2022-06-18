package org.ly.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ly.controller.function.EnumValue;
import org.ly.domain.City;
import org.ly.domain.User;
import org.ly.handler.CityHandler;
import org.ly.utils.RedisUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping("/city1")
@RequiredArgsConstructor
public class CityController {

    private final CityHandler cityHandler;
    private final RedisUtils redisUtils;

    private static final String REDIS_PREFIX_KEY = "web_flux_demo_city_1";

    @GetMapping(value = "/{id}")
    public Mono<City> findById(@PathVariable("id") Long id) {
        return cityHandler.findById(id);
    }

    @GetMapping()
    public Flux<City> findAll() {
        return cityHandler.findAll();
    }

    @PostMapping()
    public Mono<Long> save(@RequestBody City city) {
        return cityHandler.save(city);
    }

    @PutMapping()
    public Mono<Long> modify(@RequestBody City city) {
        return cityHandler.modify(city);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> delete(@PathVariable("id") Long id) {
        return cityHandler.delete(id);
    }

    @GetMapping("/redis/{id}")
    public Mono<City> findByIdInRedis(@PathVariable("id") Long id){
        String key = REDIS_PREFIX_KEY + id;
        if (redisUtils.exists(key)){
            return Mono.create(sink -> sink.success((City) redisUtils.get(key)));
        }
        return Mono.empty();
    }

    @PostMapping("/redis")
    public Mono<Boolean> saveInRedis(@RequestBody City city){
        String key = REDIS_PREFIX_KEY + city.getId();
        return Mono.create(sink -> sink.success(redisUtils.set(key, city)));
    }

    @DeleteMapping("/redis/{id}")
    public Mono<Boolean> deleteInRedis(@PathVariable("id") Long id){
        String key = REDIS_PREFIX_KEY + id;
        if (redisUtils.exists(key)){
            redisUtils.remove(key);
            return Mono.just(true);
        }
        return Mono.just(false);
    }

    @GetMapping("/error")
    public Mono<String> error(){
        return Mono.error(new RuntimeException("error test"));
    }

    @GetMapping("/validation1")
    public Mono<String> validation1(@Valid User user){
        log.info("validation1 user:" + user);
        return Mono.just("validation1 success");
    }

    @GetMapping("/validation2")
    public Mono<String> validation2(@EnumValue(intValues = {0, 1, 2}, message = "Invalid blockchain value")
                                        @RequestParam(name = "blockchain", defaultValue = "0") Integer blockchain){
        log.info("validation2 blockchain:" + blockchain);
        return Mono.just("validation2 success");
    }

    @PostMapping("/validation3")
    public Mono<String> validation3(@Valid @RequestBody User user){
        log.info("validation3 user:" + user);
        return Mono.just("validation3 success");
    }
}
