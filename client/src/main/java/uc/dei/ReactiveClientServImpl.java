package uc.dei;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import uc.dei.templates.Owner;
import uc.dei.templates.OwnerDTO;
import uc.dei.templates.OwnerPetCount;
import uc.dei.templates.Pet;
import uc.dei.templates.PetDTO;

//TODO Implement all the functions of ReactiveClientServ (don't forget @Override) and the setup of the webclient
//setup the web client and the endpoints for each method declared in the other class
public class ReactiveClientServImpl implements ReactiveClientServ{
    private WebClient wc;

    public ReactiveClientServImpl(){
        this.wc = WebClient.builder()
                            .baseUrl("http://localhost:8080/api")
                            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .build();
    }

    @Override
    public Flux<Owner> getAllOwners(){
        return wc   .get()
                    .uri("/owner")
                    .retrieve()
                    .bodyToFlux(Owner.class);
    }

    @Override
    public Mono<Owner> getOwner(long id){
        return wc   .get()
                    .uri(uriBuilder -> {
                        return uriBuilder   .path("/owner/{id}")
                                            .build(id);
                    })
                    .retrieve()
                    .bodyToMono(Owner.class);
    }

    @Override
    public Flux<Pet> getAllPets(){
        return wc   .get()
                    .uri("/pet")
                    .retrieve()
                    .bodyToFlux(Pet.class);
    }

    @Override
    public Mono<Pet> getPet(long id){
        return wc   .get()
                    .uri(uriBuilder -> {
                        return uriBuilder   .path("/pet/{id}")
                                            .build(id);
                    })
                    .retrieve()
                    .bodyToMono(Pet.class);
    }

    @Override
    public Flux<Pet> getPetsByOwner(long id){
        return wc   .get()
                    .uri(uriBuilder -> {
                        return uriBuilder   .path("/pet/ownerId/{id}")
                                            .build(id);
                    })
                    .retrieve()
                    .bodyToFlux(Pet.class);
    }

    @Override
    public Flux<OwnerPetCount> getPetCount(){
        return wc. get()
            .uri("/owner/getPets")
            .retrieve()
            .bodyToFlux(OwnerPetCount.class);
    }


    @Override
    public void postOwner(Owner owner){
        wc.post()
            .uri("/owner")
            .body(Mono.just(new OwnerDTO(owner)), OwnerDTO.class)
            .retrieve()
            .bodyToMono(String.class)
            .log()
            .subscribe();
    }

    @Override
    public void postPet(Pet pet){
        wc.post()
            .uri("/pet")
            .body(Mono.just(new PetDTO(pet)), PetDTO.class)
            .retrieve()
            .bodyToMono(String.class)
            .log()
            .subscribe();
    }
    
    
}
