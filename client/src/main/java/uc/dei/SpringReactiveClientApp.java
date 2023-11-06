package uc.dei;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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

//TODO actually call all the functions the project requires, write on stuff if needed, etc.

public class SpringReactiveClientApp {
    

    public static void main(String[] args){
        ReactiveClientServ wc = new ReactiveClientServImpl();


        //TODO insert all the project features here after making the functions below
        //#1
        getOwners(wc);

        //#2
        getNumberPets(wc);

        //#3
        getDogs(wc);

        //#4
        getHeavyPetsByWeight(wc);

        //#5

        //#6

        //#7

        //#8

        //#9
        
    }


    //These are for testing only, do all definite functions above
    //Works
    public static void getOwnerByID(ReactiveClientServ wc, long id){
        wc.getOwner(id)
            .subscribe(owner -> {
                System.out.println(owner.toString());
            });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //#1 - Show names and telephones of all Owners
        //-> simple GET
    public static void getOwners(ReactiveClientServ wc){
        System.out.println("//////////////////EX1//////////");
        wc.getAllOwners()
            .subscribe(owner -> {
                System.out.println(owner.toString());
            });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //#2 - number of Pets
        //-> simple count/size of Flux returned
    public static void getNumberPets(ReactiveClientServ wc){
        System.out.println("/////////////////EX2////////////");
        wc.getAllPets()
           .count()
           .subscribe(count->{
            System.out.println(count);
           }); 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //#3 - Total number of dogs
        //-> simple GET with species=="dog"
    public static void getDogs(ReactiveClientServ wc){
        System.out.println("/////////////////EX3////////////");
        wc.getAllPets()
            .filter(pet -> pet.getSpecies().equals("dog"))
            .count()
            .subscribe(count ->{
                System.out.println(count);
            });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //#4 - Animals weighting more than 10kg, by ascending order of weight
        //-> GET with ordering
    public static void getHeavyPetsByWeight(ReactiveClientServ wc){
        System.out.println("/////////////////EX4////////////");
        wc.getAllPets()
            .filter(pet -> pet.getWeight() > 10)
            .sort((pet1, pet2) -> Float.compare(pet1.getWeight(), pet2.getWeight()))
            .subscribe(pet -> {
                System.out.println(pet);
            });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //#5 - Average and standard deviations of animal weights
        //-> GET of all animal weights using the size of returned query to divide the sum of weights
    public static void weightAverageStdDeviation(ReactiveClientServ wc){
        System.out.println("/////////////////EX5////////////");
        wc.getAllPets()
            .map(list ->{
                float avg=0;
                for(Pet p: list){
                    avg+=p.getWeight();
                }
                //return avg/list.size();
            })
    }

    //#6 - Name of the eldest pet
        //-> GET oldest pet, return the name


    //#7 - Average number of Pets per Owner, considering Owners with only one animal
        //-> filter owners with only one pet, group pets per owner? TODO check this later


    //#8 - All names of all the Owners and number of their respective Pets, sorted by number of pets (should not use block() if possible)
        //-> GET all owners, and cross each one with another GET of Pets with them as their owner, return the count of that query, and sort Owners by that


    //#9 - Same as #8, but instead of printing just the number of Pets, prints a list of all the Pet names. (Most work should occur on the client, aka, client does all the filtering etc.)
        //-> Same as above but also print all Pets names instead of just count


}


//The request that worked in the class is here

//addOwner(wc);
        //WebClient wc = WebClient.create("http://localhost:8080/api");

        /*
        System.out.println("before");
        Mono<String> response = wc.get()
                                    .uri("/getOwner")
                                    .accept(MediaType.APPLICATION_JSON)
                                    .retrieve()
                                    .bodyToMono(String.class)
                                    .log();
                                    
        response.subscribe(result -> {
            System.out.println("Response: " + result);
        });
        
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        };
        

        System.out.println("after");*/