package io.javasmithy;

import io.javasmithy.algorithms.Algorithm;
import io.javasmithy.algorithms.AlgorithmFactory;

import java.util.*;

public class App {
    public static void main( String[] args ) {
        List<String> names = new ArrayList(Arrays.asList("naive", "rk", "kmp"));

        String pattern = "yikes";
        String text = "asdfyikesyikyikesy1900asdfareadsyikesfa3rsdr34t6234wergfsdjjhkgh3asdfalksjr";

        Map<String, Algorithm> algorithms = new HashMap<String, Algorithm>();
        for (String name: names) {
            algorithms.put(name, AlgorithmFactory.getAlgorithm(name));
            algorithms.get(name).process(pattern, text);
        }


    }
}
