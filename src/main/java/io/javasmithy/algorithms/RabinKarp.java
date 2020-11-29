package io.javasmithy.algorithms;

import java.lang.Math;

public class RabinKarp implements Algorithm{

    public void process(String pattern, String text){
        System.out.println("\nStarting Rabin Karp:");

        char[] p = pattern.toCharArray();
        char[] t = text.toCharArray();

        // a-z, 0-9 and space
        int d = 37;
        int n = t.length;
        int m = p.length;

        // figure out how to actually calculate a good mod value
        int q = 97;
        int pHash = preProcess(p, m, d, q);
        int t0Hash = preProcess(text.substring(0,m).toCharArray(), m, d, q);
        match(t, p, t0Hash, pHash, q, d, m, n);
    }

    public static int preProcess(char[] c, int m, int d, int q){
        int hash = 0;
        for (int i = 0; i < m; i++){
            hash = (d*hash + c[i]);
            hash = Math.floorMod(hash, q);
        }
        return hash;
    }

    public static void match(char[] T, char[] P,  int t0, int p, int q, int d, int m, int n){
        int exponent = m-1;
        int h = (int) Math.pow(d, exponent);
        h = Math.floorMod(h, q);

        int tShiftHash = t0;
        for (int s = 0; s <= n-m; s++){
            //System.out.println("Shift: " + s);
            //printComparisonState(T, s, m);
            //System.out.println("p: " + p);
            //System.out.println("t_" + s + " : " + tShiftHash);

            if (p == tShiftHash) {
                boolean isMatch = true;
                for (int i = 0; i < m; i++){
                    if (P[i] != T[i+s]){ isMatch = false; break;}
                }
                if (isMatch) {System.out.println("Valid Shift at shift: " + s);}
                //else { System.out.println(" !! Spurious Hit. !!");}
            } else {
                //System.out.println("Invalid Shift.");
            }

            if (s < n-m){
                int discardedHash = tShiftHash - (T[s] * h);
                int addedHash = T[s+m];
                int intermediateStep = ((d*discardedHash)+addedHash);
                tShiftHash = Math.floorMod(intermediateStep, q);
            }
            //System.out.println("\n--------");
        }
    }

    public static void printComparisonState(char[] T, int s, int m){
        System.out.print("Target Window: ");
        for (int i = s; i < s+m; i++){
            System.out.print(T[i] + "");
        }
        System.out.println("");
    }

}
