package uc.dei;

import java.time.LocalDate;
import java.util.ArrayList;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;

import uc.dei.templates.Owner;
import uc.dei.templates.Pet;

//TODO actually call all the functions the project requires, populate database if not in init_db.sql, write on stuff if needed, etc.

/*
 * TODO 
 * #1 - Show names and telephones of all Owners
 *      -> simple GET
 * #2 - TotalSpringReactiveServerAppmber of Pets
 *      -> simple count/size of Flux returned
 * #3 - Total number of dogs
 *      -> simple GET with species=="dog"
 * #4 - Animals weighting more than 10kg, by ascending order of weight
 *      -> GET with ordering
 * #5 - Average and standard deviations of animal weights
 *      -> GET of all animal weights using the size of returned query to divide the sum of weights
 * #6 - Name of the eldest pet
 *      -> GET oldest pet, return the name
 * #7 - Average number of Pets per Owner, considering Owners with only one animal
 *      -> filter owners with only one pet, group pets per owner? TODO check this later
 * #8 - All names of all the Owners and number of their respective Pets, sorted by number of pets (should not use block() if possible)
 *      -> GET all owners, and cross each one with another GET of Pets with them as their owner, return the count of that query, and sort Owners by that
 * #9 - Same as #8, but instead of printing just the number of Pets, prints a list of all the Pet names. (Most work should occur on the client, aka, client does all the filtering etc.)
 *      -> Same as above but also print all Pets names instead of just count
 * 
 * TODO
 *  - Search threads optimization for this
 *  - use .log() on flux for more info
 * 
 */
public class ClientApplication {
    

    public static void main(String[] args){
        //ReactiveClientServ wc = new ReactiveClientServImpl();
        //addOwner(wc);
        WebClient wc = WebClient.create("http://localhost:8080/api");

        System.out.println("before");
        Mono<String> response = wc.get()
                                    .uri("/getOwner")
                                    .accept(MediaType.APPLICATION_JSON)
                                    .retrieve()
                                    .bodyToMono(String.class);
                                    
        response.subscribe(result -> {
            System.out.println("Response: " + result);
        });
        

        System.out.println("after");
    }


    //These are for testing only, do all definite functions above
    public static void addOwner(ReactiveClientServ wc){
        System.out.println("trying to post it");
        Owner testDummy = new Owner(Long.valueOf(3), "tiago", 98765);
        wc.postOwner(testDummy);
        try{
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("posted it, tried at least lmao");
    }
}
