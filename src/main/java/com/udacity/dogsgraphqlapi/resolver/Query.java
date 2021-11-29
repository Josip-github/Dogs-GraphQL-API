package com.udacity.dogsgraphqlapi.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.dogsgraphqlapi.model.Dog;
import com.udacity.dogsgraphqlapi.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs(){
        return dogRepository.findAll();
    }

    public Optional<Dog> findDogById(Long id){
        return dogRepository.findById(id);
    }
}
