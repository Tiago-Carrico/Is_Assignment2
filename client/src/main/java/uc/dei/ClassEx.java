package uc.dei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;

import reactor.core.publisher.Flux;

public class ClassEx {
    
    public static void main( String[] args ){

        ArrayList<Integer> numbers = new ArrayList<Integer>();

        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);

        //Ex 1
        /*
        Consumer<Integer> method = 
            n -> {System.out.println(n);};


        numbers.forEach(method);
        */

        //EX 1
        //Sort order reversed but I see no problems in my logic
        /*
        Collections.sort(numbers, (n1, n2) -> (n1 > n2) ? -1:
                                    (n1 < n2) ? 1 : 0);

        System.out.println(numbers);
        */

        //Ex 2
        /*
        Comparator<Integer> descending = (a, b) -> {
            return a.equals(0) 
            ? 0
            : a.compareTo(b) < 0
                ? 1
                : -1;
        };
        */

        //Ex 3
        //System.out.println(descending.compare(1,2));

        //Ex 4
        /*
        Flux.range(1, 10)
            .map(v -> 10 * v)
            .filter(v -> v > 30)
            .subscribe(System.out::println);
            */
        
        //Ex 5
        /*
        Flux.range(1, 10)
            .map(n -> "Number " + n)
            .subscribe(System.out::println);
            */

        //Ex 6
        /*
        Flux.range(0, 20)
            .buffer(7, 1)   //sets a window of 7 to treat as a sublist
            .map(l -> {
                float avg=0;
                for(int i: l){
                    avg+=i;
                }
                return avg/l.size();
            })
            .subscribe(System.out::println);
            */


        //Ex 7
        /*
        Flux.range(0, 10)
            .collectList()              //equivalent to toList()
            .subscribe(System.out::println);
            */
        
        //Ex 8
        /*
        Integer[] arr8 = {1,2,3,4,5,1,0,9,10,8};
        Flux.fromArray(arr8)
            .buffer(2)
            .filter(pair -> {
                return pair.get(0).compareTo(pair.get(1)) > 0;
            })
            .concatMapIterable(x -> x)
            .subscribe(System.out::println);
            */
        
        //Ex 9

        


    }
}
