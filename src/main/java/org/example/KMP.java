package org.example;

public class KMP {
    // KMP search function: finds all pattern occurrences in text and prints their indices
    void KMPSearch(String pattern, String text) {
        int M = pattern.length();
        int N = text.length();

        // Create lps[] array to hold longest prefix suffix values for pattern
        int[] lps = new int[M];
        int j = 0; // Index for pattern

        // Preprocess the pattern to calculate lps[] array
        computeLPSArray(pattern, M, lps);

        int i = 0; // Index for text
        while (i < N) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern at index " + (i - j));
                j = lps[j - 1]; // Prepare to search for next occurrence
            }
            // Mismatch after j matches
            else if (i < N && pattern.charAt(j) != text.charAt(i)) {
                // Use the LPS array to efficiently skip redundant comparisons
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
    }

    // Builds the LPS (Longest Prefix Suffix) array for the pattern string
    void computeLPSArray(String pattern, int M, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0; // LPS for the first character is always zero

        // Calculate the LPS array
        while (i < M) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0)
                    len = lps[len - 1]; // Fallback in pattern using LPS
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    // Main method: runs three required test cases (short, medium, long string)
    public static void main(String[] args) {
        // Short example
        String text1 = "Hey Nurzhan, Nurzhan is here!";
        String pattern1 = "Nurzhan";
        System.out.println("Short text:");
        new KMP().KMPSearch(pattern1, text1);

        // Medium example
        String text2 = "I love pizza. Pizza nights are the best and pizza brings people together.";
        String pattern2 = "pizza";
        System.out.println("\nMedium text:");
        new KMP().KMPSearch(pattern2, text2);

        // Long example
        String text3 = "Java programming is fun. Java makes complex problems easier. Studying computer science with Java opens up a world of possibilities. I hope to become really skilled at programming in Java.";
        String pattern3 = "Java";
        System.out.println("\nLong text:");
        new KMP().KMPSearch(pattern3, text3);
    }
}
