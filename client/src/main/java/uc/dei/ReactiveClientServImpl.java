package uc.dei;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import uc.dei.templates.Owner;
import uc.dei.templates.OwnerDTO;
import uc.dei.templates.Pet;
import uc.dei.templates.PetDTO;

//TODO Implement all the functions of ReactiveClientServ (don't forget @Override) and the setup of the webclient
//setup the web client and the endpoints for each method declared in the other class
public class ReactiveClientServImpl implements ReactiveClientServ{
    private WebClient wc;

    public ReactiveClientServImpl(){
        this.wc = WebClient .builder()
                            .baseUrl("http://localhost:8080/api")
                            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .build();
    }


    //test functions, put all definite ones above
    @Override
    public void postOwner(Owner owner){
        wc  .post()
            .uri("/owner")
            .body(Mono.just(new OwnerDTO(owner)), OwnerDTO.class)
            .retrieve()
            .bodyToMono(String.class)
            .subscribe();
    }
}
