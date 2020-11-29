package io.javasmithy.algorithms;

import java.util.ArrayDeque;
import java.util.Queue;

public class RunnableKMP implements Runnable {
    Algorithm a;
    String pattern;
    String text;


    public RunnableKMP(Algorithm a, String pattern, String text){
        this.a = a;
        this.pattern = pattern;
        this.text = text;
    }

    @Override
    public void run() {


    }
}

