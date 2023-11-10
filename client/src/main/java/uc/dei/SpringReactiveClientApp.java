package uc.dei;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException{
        ReactiveClientServ wc = new ReactiveClientServImpl();
        
        //Creating all file writers to dump results in
        try {
            File fil1 = new File("Ex1.txt");
            FileWriter myWriter1 = new FileWriter("Ex1.txt");
            //#1
            getOwners(wc, myWriter1);    //Done

            File fil2 = new File("Ex2.txt");
            FileWriter myWriter2 = new FileWriter("Ex2.txt");
            //#2
            getNumberPets(wc, myWriter2);    //Done
            
            File fil3 = new File("Ex3.txt");
            FileWriter myWriter3 = new FileWriter("Ex3.txt");
            //#3
            getDogs(wc, myWriter3);  //Done

            File fil4 = new File("Ex4.txt");
            FileWriter myWriter4 = new FileWriter("Ex4.txt");
            //#4
            getHeavyPetsByWeight(wc, myWriter4); //Done

            File fil5 = new File("Ex5.txt");
            FileWriter myWriter5 = new FileWriter("Ex5.txt");
            //#5
            weightAverageStdDeviation(wc, myWriter5);    //Done

            File fil6 = new File("Ex6.txt");
            FileWriter myWriter6 = new FileWriter("Ex6.txt");
            //#6
            eldestPet(wc, myWriter6);    //Done

            File fil7 = new File("Ex7.txt");
            FileWriter myWriter7 = new FileWriter("Ex7.txt");
            //#7
            avgPetsPerOwner(wc, myWriter7);

            File fil8 = new File("Ex8.txt");
            FileWriter myWriter8 = new FileWriter("Ex8.txt");
            //#8
            ownersAndPetNr(wc, myWriter8);

            File fil9 = new File("Ex9.txt");
            FileWriter myWriter9 = new FileWriter("Ex9.txt");
            //#9
            ownerAndPets(wc, myWriter9);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        semaphore.acquire(9);
        
    }
  
    //#1 - Show names and telephones of all Owners
        //-> simple GET
    public static void getOwners(ReactiveClientServ wc, FileWriter writer){
        //System.out.println("//////////////////EX1//////////");
        wc.getAllOwners()
            .subscribe(owner -> {
                //System.out.println(owner.toString());
                try {
                    writer.write(owner.toString()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, err -> {
                logger.error("Error while retrieving all owners: ", err);
                
            }, () -> {logger.info("All owners retrieved.");
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                semaphore.release(1);});
    }

    //#2 - number of Pets
        //-> simple count/size of Flux returned
    public static void getNumberPets(ReactiveClientServ wc, FileWriter writer){
        //System.out.println("/////////////////EX2////////////");
        wc.getAllPets()
           .count()
           .subscribe(count->{
            //System.out.println(count);
            try {
                writer.write(String.valueOf(count));
            } catch (IOException e) {
                e.printStackTrace();
            }
           }, err -> {
            logger.error("Error while counting pets: ", err);
           }, () -> {logger.info("All pets counted.");
           try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            semaphore.release(1);}); 
    }

    //#3 - Total number of dogs
        //-> simple GET with species=="dog"
    public static void getDogs(ReactiveClientServ wc, FileWriter writer){
        //System.out.println("/////////////////EX3////////////");
        wc.getAllPets()
            .filter(pet -> pet.getSpecies().equals("dog"))
            .count()
            .subscribe(count ->{
                //System.out.println(count);
                try {
                writer.write(String.valueOf(count));
            } catch (IOException e) {
                e.printStackTrace();
            }
            }, err -> {
                logger.error("Error while retrieving all dogs:", err);
            }, () -> {logger.info("Total number of dogs retrieved.");
                try {
                    writer.close();
                    } catch (IOException e) {
                    e.printStackTrace();
                }
                semaphore.release(1);});
    }

    //#4 - Animals weighting more than 10kg, by ascending order of weight
        //-> GET with ordering
    public static void getHeavyPetsByWeight(ReactiveClientServ wc, FileWriter writer){
        //System.out.println("/////////////////EX4////////////");
        wc.getAllPets()
            .filter(pet -> pet.getWeight() > 10)
            .sort((pet1, pet2) -> Float.compare(pet1.getWeight(), pet2.getWeight()))
            .subscribe(pet -> {
                //System.out.println(pet);
                try {
                    writer.write(pet+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, err -> {
                logger.error("Error while retrieving animals heavier thank 10kg: ", err);
            }, () -> {logger.info("Animals heavier than 10kg retrieved.");
                try {
                    writer.close();
                    } catch (IOException e) {
                    e.printStackTrace();
                }
                semaphore.release(1);});
    }

    //#5 - Average and standard deviations of animal weights
        //-> GET of all animal weights using the size of returned query to divide the sum of weights
    public static void weightAverageStdDeviation(ReactiveClientServ wc, FileWriter writer){
        //System.out.println("/////////////////EX5////////////");
        //Average
        wc.getAllPets()
            .map(pet -> pet.getWeight())
            .reduce(new SumAndCount(0,0), (acc, value)-> acc.accumulate(value))
            .subscribe(sum -> {
                float avg = sum.getSum()/sum.getCount();
                //System.out.println("Average= " + avg);    //works

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
                        //System.out.println("Standard deviation = " + stdDev);
                        try {
                            writer.write("Average weight: " + avg + "\nStandard deviation: " + stdDev +"\n");
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //TODO make writer work here
                    });

            }, err -> {
                logger.error("Error while retrieving average and standard deviation.");
            }, () -> {logger.info("Average and standard deviation of all animal weights retrieved.");
                semaphore.release(1);});

    }

    //#6 - Name of the eldest pet
        //-> GET oldest pet, return the name
    public static void eldestPet(ReactiveClientServ wc, FileWriter writer){
        //System.out.println("/////////////////EX6////////////");
        wc.getAllPets()
            .sort((pet1, pet2) -> pet1.getBirthDate().compareTo(pet2.getBirthDate()))
            .take(1)
            .subscribe(eldest -> {
                //System.out.println(eldest.getName());
                try {
                    writer.write(eldest.getName()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, err -> {
                logger.error("Error retrieving oldest pet: ", err);
            }, () -> {logger.info("Retrieved oldest pet.");
                try {
                    writer.close();
                    } catch (IOException e) {
                    e.printStackTrace();
                }
                semaphore.release(1);});
    }


    //#7 - Average number of Pets per Owner, considering Owners with only more than one animal
        //-> filter owners with only one pet, group pets per owner? TODO check this later
        //
    public static void avgPetsPerOwner(ReactiveClientServ wc, FileWriter writer){
        //System.out.println("/////////////////EX7////////////");

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
                //System.out.println(avg);    //works
                try {
                    writer.write(avg+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, err -> {
                logger.error("Error retrieving average number of pets per owner: ", err);
            }, () -> {logger.info("Retrieved average number of pets per owner.");
                try {
                    writer.close();
                    } catch (IOException e) {
                    e.printStackTrace();
                }
                semaphore.release(1);});
    }

    //#8 - All names of all the Owners and number of their respective Pets, sorted by number of pets (should not use block() if possible)
        //-> GET all owners, and cross each one with another GET of Pets with them as their owner, return the count of that query, and sort Owners by that
        //if you can do the above one, you can probably already get the pets of each owner, and as such can probs just print owner name and number of pets returned
    public static void ownersAndPetNr(ReactiveClientServ wc, FileWriter writer){
        //System.out.println("/////////////////EX8////////////");
        wc.getPetCount()
            .subscribe(count -> {
                try {
                    writer.write(count+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, err -> {
                logger.error("Error retrieving owners and their pet count: ", err);
            }, () -> {logger.info("Retrieved owners and their pet count.");
                try {
                    writer.close();
                    } catch (IOException e) {
                    e.printStackTrace();
                }
                semaphore.release(1);});      
    }

    //#9 - Same as #8, but instead of printing just the number of Pets, prints a list of all the Pet names. (Most work should occur on the client, aka, client does all the filtering etc.)
        //-> Same as above but also print all Pets names instead of just count
        //If you can do the above one, instead of returning a number or something, just print all the pets lmao

    public static void ownerAndPets(ReactiveClientServ wc, FileWriter writer){
        //System.out.println("/////////////////EX9////////////");
        wc.getAllOwners()
            .concatMap(owner -> {
                //System.out.println("\nOwner name: " + owner.getName());
                try {
                    writer.write("\nOwner name: " + owner.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return wc.getPetsByOwner(owner.getId())
                            .doOnNext(pet -> /*System.out.println(pet.getName())*/{
                                try {
                                    writer.write(pet+"\n");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
            })
            .subscribe((pet) -> {}, err -> {
                logger.error("Error retrieving owners and their pets.");
            }, () -> {logger.info("Retrieved owners and their pets.");
                try {
                    writer.close();
                    } catch (IOException e) {
                    e.printStackTrace();
                }
                semaphore.release(1);
            });
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
