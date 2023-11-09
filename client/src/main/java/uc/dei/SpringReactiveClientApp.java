package uc.dei;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
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
import uc.dei.templates.SumAndCount;

//TODO actually call all the functions the project requires, write on stuff if needed, etc.

public class SpringReactiveClientApp {
    
    static Logger logger = LoggerFactory.getLogger(SpringReactiveClientApp.class);

    private static Semaphore semaphore = new Semaphore(9);

    public static void main(String[] args) throws InterruptedException{
        ReactiveClientServ wc = new ReactiveClientServImpl();
        


        //TODO insert all the project features here after making the functions below
        //#1
        getOwners(wc);    //Done

        //#2
        getNumberPets(wc);    //Done

        //#3
        getDogs(wc);  //Done

        //#4
        getHeavyPetsByWeight(wc); //Done

        //#5
        weightAverageStdDeviation(wc);    //Done

        //#6
        eldestPet(wc);    //Done

        //#7
        avgPetsPerOwner(wc);

        //#8
        ownersAndPetNr(wc);

        //#9
        ownerAndPets(wc);


        //semaphore.acquire(9);
        //test(wc);
        
    }
  
    //#1 - Show names and telephones of all Owners
        //-> simple GET
    public static void getOwners(ReactiveClientServ wc){
        System.out.println("//////////////////EX1//////////");
        wc.getAllOwners()
            .subscribe(owner -> {
                System.out.println(owner.toString());
            }, err -> {
                logger.error("Error while retrieving all owners: ", err);
                
            }, () -> {logger.info("All owners retrieved.");
                semaphore.release(1);});
    }

    //#2 - number of Pets
        //-> simple count/size of Flux returned
    public static void getNumberPets(ReactiveClientServ wc){
        System.out.println("/////////////////EX2////////////");
        wc.getAllPets()
           .count()
           .subscribe(count->{
            System.out.println(count);
           }, err -> {
            logger.error("Error while counting pets: ", err);
           }, () -> {logger.info("All pets counted.");
            semaphore.release(1);}); 
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
            logger.info("Total number of dogs retrieved.");
            Thread.sleep(10000);
        } catch (InterruptedException e){
            logger.error("Error while retrieving all dogs: ", e);
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
            logger.info("Animals heavier than 10kg retrieved.");
            Thread.sleep(1000);
        } catch (InterruptedException e){
            logger.error("Error while retrieving animals heavier than 10kg: ", e);
            e.printStackTrace();
        }
    }

    //#5 - Average and standard deviations of animal weights
        //-> GET of all animal weights using the size of returned query to divide the sum of weights
    public static void weightAverageStdDeviation(ReactiveClientServ wc){
        System.out.println("/////////////////EX5////////////");
        //Average
        wc.getAllPets()
            .map(pet -> pet.getWeight())
            .reduce(new SumAndCount(0,0), (acc, value)-> acc.accumulate(value))
            .subscribe(sum -> {
                float avg = sum.getSum()/sum.getCount();
                System.out.println("Average= " + avg);    //works

                wc.getAllPets()
                    .map(pet -> Math.pow((pet.getWeight() - avg), 2)) //gets weight, subtracts average and turns into that value squared
                    .collectList()
                    .subscribe( weightList -> {
                        float avgDev = 0;

                        for(int i = 0; i < weightList.size(); i++){
                            avgDev += weightList.get(i);
                        }
                        avgDev = avgDev/weightList.size();
                        double stdDev = Math.sqrt(avgDev);
                        System.out.println("Standard deviation = " + stdDev);
                    });

            });

        /* 
        wc.getAllPets()
            .map(pet -> pet.getWeight())
            .collectList()
            .subscribe(weightList -> {
                //Average
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
            });*/
        try {
            logger.info("Average and standard deviation of all animals retrieved.");
            Thread.sleep(10000);
        } catch (InterruptedException e){
            logger.error("Error while retrieving average and standard deviation: ", e);
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
            logger.info("Retrieved oldest pet.");
            Thread.sleep(1000);
        } catch (InterruptedException e){
            logger.error("Error retrieving oldest pet: ", e);
            e.printStackTrace();
        }
    }


    //#7 - Average number of Pets per Owner, considering Owners with only more than one animal
        //-> filter owners with only one pet, group pets per owner? TODO check this later
        //
    public static void avgPetsPerOwner(ReactiveClientServ wc){
        System.out.println("/////////////////EX7////////////");

        /*
        wc.getAllOwners()
            .map(owner -> wc.getPetsByOwner(owner.getId())
                            .count()
                            .subscribe(System.out::println))
            //.filter(petCount -> petCount > 1)
            .subscribe(System.out::println); */
        wc.getPetCount()
            .filter(owner -> owner.getPetCount() > 1)
            .reduce(new SumAndCount(0,0), (acc, owner)-> acc.accumulate(owner.getPetCount()))
            .subscribe(sum -> {
                float avg = sum.getSum()/sum.getCount();
                System.out.println(avg);    //works
            });
        try {
            logger.info("Retrieved average pets per owner.");
            Thread.sleep(1000);
        } catch (InterruptedException e){
            logger.error("Error retrieving average number of pets per owner: ", e);
            e.printStackTrace();
        }

    }

    //#8 - All names of all the Owners and number of their respective Pets, sorted by number of pets (should not use block() if possible)
        //-> GET all owners, and cross each one with another GET of Pets with them as their owner, return the count of that query, and sort Owners by that
        //if you can do the above one, you can probably already get the pets of each owner, and as such can probs just print owner name and number of pets returned
    public static void ownersAndPetNr(ReactiveClientServ wc){
        System.out.println("/////////////////EX8////////////");
        wc.getPetCount()
            .subscribe(System.out::println);
        try {
            logger.info("Retrieved Owners and their pet count.");
            Thread.sleep(1000);
        } catch (InterruptedException e){
            logger.error("Error retrieving owners and pet count: ", e);
            e.printStackTrace();
        }
            
    }

    //#9 - Same as #8, but instead of printing just the number of Pets, prints a list of all the Pet names. (Most work should occur on the client, aka, client does all the filtering etc.)
        //-> Same as above but also print all Pets names instead of just count
        //If you can do the above one, instead of returning a number or something, just print all the pets lmao

    public static void ownerAndPets(ReactiveClientServ wc){
        System.out.println("/////////////////EX9////////////");
        wc.getAllOwners()
            .concatMap(owner -> {
                System.out.println("\nOwner name: " + owner.getName());
                return wc.getPetsByOwner(owner.getId())
                            .doOnNext(pet -> System.out.println(pet.getName()));
            })
            .subscribe();
        try {
            logger.info("Retrieved Owners and their pet count.");
            Thread.sleep(1000);
        } catch (InterruptedException e){
            logger.error("Error retrieving owners and pet count: ", e);
            e.printStackTrace();
        }
    }

    /*
        wc.getAllOwners()
            .subscribe(owner -> {
                System.out.println(owner);
                wc.getPetsByOwner(owner.getId())
                .subscribe(pet -> {
                    System.out.println(pet);
                });
            });*/



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

    public static void test(ReactiveClientServ wc){
        wc.getPetsByOwner(2)
        .subscribe(System.out::println);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

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