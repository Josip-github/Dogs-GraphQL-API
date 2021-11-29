package com.udacity.dogsgraphqlapi.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.dogsgraphqlapi.exception.BreedNotFoundException;
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

    public boolean deleteDogBreed(String breed){
        boolean deleted = false;
        List<Dog> dogs = (List<Dog>) dogRepository.findAll();

        for (Dog dog: dogs) {
            if(dog.getBreed().equals(breed)){
                dogRepository.delete(dog);
                deleted = true;
            }
        }

        if(!deleted){
            throw new BreedNotFoundException("Breed not found", breed);
        }

        return deleted;
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
