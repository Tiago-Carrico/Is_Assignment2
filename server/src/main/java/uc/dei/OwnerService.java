package uc.dei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OwnerService {
    
    @Autowired
    private OwnerRepository ownerRep;

    //add new Owner or update it if it already exists (by ID)
    //TODO WARNING IT CANT BE addOwner, ITS GOTTA BE saveOwner FOR SOME GOD FORSAKEN REASON
    public Mono<Owner> saveOwner(Owner owner){
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
