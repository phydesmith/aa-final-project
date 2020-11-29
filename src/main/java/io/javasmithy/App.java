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

        //String pattern = "yikes";
        String pattern = "test";
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(App.class.getClassLoader().getResourceAsStream("text.txt")));

            String text = in.readLine();

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
            System.out.println("Multi-threaded KMP");
            for (int i = 0; i < threads.length; i++){
                threads[i]= new Thread(new RunnableKMP(algorithms.get("kmp-geeks"), pattern, text));
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
