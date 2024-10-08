package uc.dei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uc.dei.entity.Owner;
import uc.dei.entity.OwnerPetCount;
import uc.dei.service.OwnerService;
import uc.dei.entity.Pet;
import uc.dei.service.PetService;
import uc.dei.entity.OwnerPetCount;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api")
public class RESTController {
    
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private PetService petService;

    private Logger logger = LoggerFactory.getLogger(RESTController.class);

    //Needed functions
    //GET /api/owner -> returns all owners
    @GetMapping("/owner")
    public Flux<Owner> getAllOwners(){
        logger.info("Request type: GET\nRequest URI: /api/owner");
        return ownerService.getAllOwners();
    }

    // GET /api/owner/{id} -> returns the owner that corresponds with the given ID
    @GetMapping("/owner/{id}")
    public Mono<Owner> getOwner(@PathVariable("id") Long id) {
        logger.info("Request type: GET\nRequest URI: /api/owner/" + id + "]");
        return ownerService.getOwnerById(id);
    }

    //GET /api/pet -> returns all pets
    @GetMapping("/pet")
    public Flux<Pet> getAllPets(){
        logger.info("Request type: GET\nRequest URI: /api/pet");
        return petService.getAllPets();
    }

    // GET /api/pet/{id} -> returns the pet that corresponds with the given ID
    @GetMapping("/pet/{id}")
    public Mono<Pet> getPet(@PathVariable("id") Long id) {
        logger.info("Request type: GET\nRequest URI: /api/pet/" + id + "]");
        return petService.getPetById(id);
    }

    @GetMapping("/pet/ownerId/{id}")
    public Flux<Pet> getPetByOwner(@PathVariable("id") Long id){
        logger.info("Request type: GET\nRequest URI: /api/pet/ownerId/" + id + "]");
        return petService.getPetByOwnerId(id);
    }

    @GetMapping("/owner/getPets")
    public Flux<OwnerPetCount> getPetCount(){
        logger.info("Request type: GET\nRequest URI: /api/owner/getPets");
        return petService.getPetCount();
    }


    @PostMapping("/owner"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
    public Mono<Owner> addOwner(@RequestBody Owner owner){
        System.out.println("it shows something here?");
        logger.info("Request: POST | /api/owner");
        return ownerService.saveOwner(owner);
    }

    @PostMapping("/pet"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
    public Mono<Pet> addPet(@RequestBody Pet pet){
        System.out.println("it shows something here?");
        logger.info("Request: POST | /api/pet");
        return petService.savePet(pet);
    }



    //Test functions
    @GetMapping("/getOwner")
    public Mono<Owner> getOwner(){
        System.out.println("it shows something here?");
        logger.info("Request: GET | /api/owner/1");
        return ownerService.getOwnerById((long) 1);
    }
    
}
