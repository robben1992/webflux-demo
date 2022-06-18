package org.ly.repository;

import org.ly.domain.City;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

@Repository
public class CityRepository {

    private ConcurrentHashMap<Long, City> repository = new ConcurrentHashMap<>();

    private static final LongAdder idGenerator = new LongAdder();

    public Long save(City city){
        Long id = idGenerator.longValue();
        city.setId(id);
        repository.put(id, city);
        idGenerator.increment();
        return id;
    }

    public Collection<City> findAll(){
        return repository.values();
    }

    public City findById(Long id){
        return repository.get(id);
    }

    public Long update(City city){
        repository.put(city.getId(), city);
        return city.getId();
    }

    public Long delete(Long id){
        repository.remove(id);
        return id;
    }

}
