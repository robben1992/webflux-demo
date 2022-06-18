package org.ly.controller;

import lombok.RequiredArgsConstructor;
import org.ly.domain.City2;
import org.ly.handler.CityHandler2;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/city2")
@RequiredArgsConstructor
public class CityController2 {

    private final CityHandler2 cityHandler;

    @GetMapping(value = "/name/count")
    public Mono<Long> countByName(@RequestParam("name") String name) {
        return cityHandler.countByCityNameLike(name);
    }

    @GetMapping(value = "/name/page")
    public Flux<City2> findPageByName(@RequestParam("name") String name,
                                  @RequestParam("page") Integer page,
                                  @RequestParam("size") Integer size) {
        return cityHandler.findPageByNameLike(name,page,size);
    }

    @GetMapping(value = "/name")
    public Mono<City2> findByName(@RequestParam("name") String name) {
        return cityHandler.findByName(name);
    }

    @GetMapping(value = "/{id}")
    public Mono<City2> findById(@PathVariable("id") String id) {
        return cityHandler.findById(id);
    }

    @GetMapping()
    public Flux<City2> findAll() {
        return cityHandler.findAll();
    }

    @PostMapping()
    public Mono<City2> save(@RequestBody City2 city) {
        return cityHandler.save(city);
    }

    @PutMapping()
    public Mono<Boolean> modify(@RequestBody City2 city) {
        return cityHandler.modify(city);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Boolean> delete(@PathVariable("id") String id) {
        return cityHandler.delete(id);
    }
}
