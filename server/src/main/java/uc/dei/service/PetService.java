package uc.dei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uc.dei.entity.Pet;
import uc.dei.repository.PetRepository;

import uc.dei.entity.OwnerPetCount;

@Service
public class PetService {
    
    @Autowired
    private PetRepository petRep;

    //add new Pet or update it if it already exists (by ID)
    public Mono<Pet> savePet(Pet pet){
        return petRep.save(pet);
    }

    //returns all pets
    public Flux<Pet> getAllPets(){
        return petRep.findAll();
    }

    //gets a mono with the pet with the corresponding ID
    public Mono<Pet> getPetById (Long id){
        return petRep.findById(id);
    }

    //deletes the pet with the given Id on the repository/database
    public Mono<Void> deletePet(Long id){
        return petRep.deleteById(id);
    }

    public Flux<Pet> getPetByOwnerId(long id){
        return petRep.findByOwnerId(id);
    }

    public Flux<OwnerPetCount> getPetCount(){
        return petRep.getPetCount();
    }
}
