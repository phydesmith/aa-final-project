package io.javasmithy.algorithms;

import java.util.Arrays;

public class KMP implements Algorithm{


    public void process(String pattern, String text){
        computePrefix(pattern);
        //kmpMatcher(pattern, text);
    }

    private void kmpMatcher(String pattern, String text){
        // cormen pg 1005
        char[] P = pattern.toCharArray();
        char[] T = text.toCharArray();
        int[] prefixArr = computePrefix(pattern);
        int n = T.length;
        int m = P.length;
        int q = 0;

        System.out.println(P);
        System.out.println(T);

        for (int i = 0; i < n; i++){
            System.out.println(q);
            while ( q > 0 && P[q+1] != T[i-1]){
                System.out.println(q);
                q = prefixArr[q];
            }
            if (P[q+1] == T[i]){
                q = q+1;
            }
            if (q == m){
                System.out.println("Pattern occurs at shift " + (i-m));
                q = prefixArr[q];
            }
        }
    }


    private int[] computePrefix(String pattern){

        char[] p = pattern.toCharArray();

        // cormen page 1006
        int m = pattern.length();
        int[] pi = new int[m];
        pi[0] = 0;
        int k = 0;

        //  q = 2
        //  [ 1, 2, 3, 4, 5]

        //  q = 1
        //  [ 0, 1, 2, 3, 5]
        for (int i = 1; i < m; i++){
            while ( (k > 0) && (p[k+1] != p[i]) ) {
                k = pi[k];
            }
            if (p[k+1] == p[i]) {
                k++;
                pi[i] = k;
            }
        }

        System.out.println("Prefix array: " + Arrays.toString(pi));
        return pi;
    }
// 66 63 -187

}
