package io.javasmithy.algorithms;

public class NaiveStringMatcher implements Algorithm {

    public void process(String pattern, String text){
        char[] p = pattern.toCharArray();
        char[] t = text.toCharArray();

        int searchableArea = t.length-p.length;

        for (int i = 0; i <= searchableArea; i++){
            boolean match = true;
            for (int c = 0; c < p.length; c++){
                if (t[i+c] != p[c]) match = false;
            }
            if (match){
                System.out.println("Match at index " + i + " of text.");
            }
        }
    }
}
