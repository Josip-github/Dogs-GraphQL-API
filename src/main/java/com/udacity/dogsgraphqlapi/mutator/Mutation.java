package com.udacity.dogsgraphqlapi.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.dogsgraphqlapi.exception.DogNotFoundException;
import com.udacity.dogsgraphqlapi.model.Dog;
import com.udacity.dogsgraphqlapi.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    // deleteDogBreed

    public Boolean deleteDogBreed(String breed){
        List<Dog> dogs = (List<Dog>) dogRepository.findAll();
        for (Dog dog: dogs) {
            if(dog.getBreed() == breed){
                dogs.remove(dog.getBreed());
            }
        }
        return true;
    }

    // updateDogName

    public Dog updateDogName(String newName, Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if(optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog not found", id);
        }
    }
}
