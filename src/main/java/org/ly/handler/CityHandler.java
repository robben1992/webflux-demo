package org.ly.handler;

import lombok.RequiredArgsConstructor;
import org.ly.domain.City;
import org.ly.repository.CityRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CityHandler {

    private final CityRepository cityRepository;

    public Mono<Long> save(City city){
        return Mono.create(sink -> sink.success(cityRepository.save(city)));
    }

    public Mono<City> findById(Long id){
        return Mono.justOrEmpty(cityRepository.findById(id));
    }

    public Flux<City> findAll(){
        return Flux.fromIterable(cityRepository.findAll());
    }

    public Mono<Long> modify(City city){
        return Mono.create(sink -> sink.success(cityRepository.update(city)));
    }

    public Mono<Long> delete(Long id){
        return Mono.create(sink -> sink.success(cityRepository.delete(id)));
    }

    public Mono<ServerResponse> helloCity(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello City"));
    }
}
