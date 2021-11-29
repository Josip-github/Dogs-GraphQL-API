package com.udacity.dogsgraphqlapi.repository;

import com.udacity.dogsgraphqlapi.model.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
