package io.javasmithy.algorithms;

import java.util.ArrayDeque;
import java.util.Queue;

public class RunnableNaive implements Runnable {
    Algorithm a;
    String pattern;
    String text;
    ArrayDeque<Integer> q;

    public RunnableNaive(Algorithm a, String pattern, String text, ArrayDeque<Integer> q){
        this.a = a;
        this.pattern = pattern;
        this.text = text;
        this.q = q;
    }

    @Override
    public void run() {
        System.out.println("RUNNING");
        try{
            while(!q.isEmpty()){
                int startIndex = q.poll();
                int endIndex = startIndex + pattern.length();
                this.a.process(pattern, text.substring(startIndex, endIndex));
            }

        } catch (NullPointerException e){
            System.out.println("NPE");
        }

    }
}
