package io.javasmithy.algorithms;

import java.util.ArrayDeque;
import java.util.Queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class RunnableKMP implements Runnable {
    Algorithm a;
    String pattern;
    String text;
    ArrayDeque<Integer> q;
    int k;

    public RunnableKMP(Algorithm a, String pattern, String text, ArrayDeque<Integer> q, int k){
        this.a = a;
        this.pattern = pattern;
        this.text = text;
        this.q = q;
        this.k = k;
    }

    @Override
    public void run() {
        try{
            while(!q.isEmpty()){
                int startIndex = q.poll();
                int endIndex = startIndex + (text.length()/k);
                this.a.process(pattern, text.substring(startIndex, endIndex));
            }

        } catch (NullPointerException e){
            System.out.println("NPE");
        }

    }
}

