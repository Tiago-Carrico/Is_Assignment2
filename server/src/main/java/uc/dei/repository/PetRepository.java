package uc.dei.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;
import uc.dei.entity.Pet;
import uc.dei.entity.OwnerPetCount;

public interface PetRepository extends R2dbcRepository<Pet, Long>{
    
    @Query("SELECT * FROM pet WHERE owner_id =$1")
    //@Query("SELECT * FROM pet;")
    public Flux<Pet> findByOwnerId(long id);

    @Query("SELECT owner.id, owner.name, COUNT(pet.id) AS pet_count FROM owner LEFT JOIN pet ON owner.id = pet.owner_id GROUP BY owner.id, owner.name;")
    public Flux<OwnerPetCount> getPetCount();
}
