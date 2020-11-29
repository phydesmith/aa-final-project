package io.javasmithy;

import io.javasmithy.algorithms.Algorithm;
import io.javasmithy.algorithms.AlgorithmFactory;

import io.javasmithy.algorithms.RunnableNaive;
import io.javasmithy.algorithms.RunnableKMP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main( String[] args ) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int processHT = (availableProcessors * 2);

        System.out.println(processHT);

        Thread[] threads = new Thread[processHT];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread();
        }

        //List<String> names = new ArrayList(Arrays.asList("naive", "rk", "kmp", "kmp-geeks"));
        List<String> names = new ArrayList(Arrays.asList("naive", "rk", "kmp-geeks"));

        String pattern = "uniquetestingstring";
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(App.class.getClassLoader().getResourceAsStream("sherlock.txt")));

            String text = in.lines().collect(Collectors.joining("\n"));

            int t = text.length();
            int m = pattern.length();
            int k = threads.length;


            Map<String, Algorithm> algorithms = new HashMap<String, Algorithm>();
            for (String name: names) {
                algorithms.put(name, AlgorithmFactory.getAlgorithm(name));
                algorithms.get(name).process(pattern, text);
            }

            ArrayDeque<Integer> indexes = new ArrayDeque<>();
            for(int i = 0; i < text.length()-pattern.length(); i++){
                indexes.add(i);
            }

            //  Threaded naive
            System.out.println("Multi-threaded Naive");
            ArrayDeque<Integer> naiveIndexes = indexes.clone();
            for (int i = 0; i < threads.length; i++){
                threads[i]= new Thread(new RunnableNaive(algorithms.get("naive"), pattern, text, naiveIndexes));
                threads[i].start();
                threads[i].join();
            }


            //  Threaded kmp
            ArrayDeque<Integer> kmpIndexes = new ArrayDeque<>();
            for(int i = 0; i < text.length()-pattern.length(); i++){
                kmpIndexes.add(i*k);
            }
            for (int i = 1; i < t-m; i++){
                kmpIndexes.add( (i*k) - (m-1));
            }

            for (int i = 0; i < threads.length; i++){
                threads[i]= new Thread(new RunnableKMP(algorithms.get("kmp-geeks"), pattern, text, kmpIndexes, k));
                threads[i].start();
                threads[i].join();
            }

            System.out.println("End");

            in.close();

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }





    }
}
