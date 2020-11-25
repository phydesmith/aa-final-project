package io.javasmithy.algorithms;

public class AlgorithmFactory {
    public static Algorithm getAlgorithm(String algo){
        if (algo.equalsIgnoreCase("naive")) return new NaiveStringMatcher();
        if (algo.equalsIgnoreCase("rk")) return new RabinKarp();
        if (algo.equalsIgnoreCase("kmp")) return new KMP();
        else return null;
    }
}
