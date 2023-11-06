package uc.dei.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;
import uc.dei.entity.Pet;

public interface PetRepository extends R2dbcRepository<Pet, Long>{
    
    @Query("SELECT * FROM pet WHERE owner_id =?1")
    //@Query("SELECT * FROM pet;")
    public Flux<Pet> findByOwnerId(long id);
}
