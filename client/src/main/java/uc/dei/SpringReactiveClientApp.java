package uc.dei;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;

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
        //getOwners(wc);

        //#2
        //getNumberPets(wc);

        //#3
        //getDogs(wc);

        //#4
        //getHeavyPetsByWeight(wc);

        //#5
        weightAverageStdDeviation(wc);

        //#6
        //eldestPet(wc);

        //#7
        //avgPetsPerOwner(wc);

        //#8

        //#9


        //test(wc);
        
    }


    public static void test(ReactiveClientServ wc){
        wc.getPetsByOwner(2)
        .subscribe(System.out::println);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
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
            Thread.sleep(10000);
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
        //Average
        wc.getAllPets()
           // .collectList()
            .map(pet -> pet.getWeight())
            .collectList()
            .subscribe(weightList -> {
                float avg = 0;
                for(int i = 0; i < weightList.size(); i++){
                    avg+=weightList.get(i);
                }
                avg = avg/weightList.size();
                System.out.println("Average: " + avg);  //Average works

                //Standard deviation, done here to use the average without recalculating
                final float stdDevAvg = avg;
                wc.getAllPets()
                    .map(pet -> pet.getWeight())
                    .map(weight -> weight - stdDevAvg)
                    .map(weight-> weight * weight)
                    .collectList()
                    .subscribe(weightListDev -> {
                        float avgDev = 0;
                        for(int j = 0; j < weightListDev.size(); j++){
                            avgDev+=weightListDev.get(j);
                        }
                        avgDev = avgDev/weightListDev.size();
                        double stdDev = Math.sqrt(avgDev);
                        System.out.println("Standard deviation: " + stdDev);    //Standard deviation is correct and works
                    });
            });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //#6 - Name of the eldest pet
        //-> GET oldest pet, return the name
    public static void eldestPet(ReactiveClientServ wc){
        System.out.println("/////////////////EX6////////////");
        wc.getAllPets()
            .sort((pet1, pet2) -> pet1.getBirthDate().compareTo(pet2.getBirthDate()))
            .take(1)
            .subscribe(eldest -> {
                System.out.println(eldest.getName());
            });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    //#7 - Average number of Pets per Owner, considering Owners with only more than one animal
        //-> filter owners with only one pet, group pets per owner? TODO check this later
        //
    public static void avgPetsPerOwner(ReactiveClientServ wc){
        System.out.println("/////////////////EX7////////////");
        wc.getAllOwners()
            //.map(owner -> wc.getAllPets().filter(pet -> pet.getId() == owner.getId()).count().subscribe(System.out::println))
           /* .filter(owner -> {
                wc.getAllPets()
                    .filter(pet -> pet.getId() == owner.getId())
                    .count()
                    .subscribe();
            })*/
            .subscribe();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

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