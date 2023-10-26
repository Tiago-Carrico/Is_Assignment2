package uc.dei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import uc.dei.repository.OwnerRepository;
import uc.dei.entity.Owner;

@Service
public class OwnerService {
    
    @Autowired
    private OwnerRepository ownerRep;

    //add new Owner or update it if it already exists (by ID)
    public Mono<Owner> addOwner(Owner owner){
        return ownerRep.save(owner);
    }

    //returns all owners
    public Flux<Owner> getAllOwners(){
        return ownerRep.findAll();
    }

    //gets a mono with the owner with the corresponding ID
    public Mono<Owner> getOwnerById (Long id){
        return ownerRep.findById(id);
    }

    //deletes the owner with the given Id on the repository/database
    public Mono<Void> deleteOwner(Long id){
        return ownerRep.deleteById(id);
    }

}
