package org.example.DebuggingAndTesting.UtilititiesChallenge;

public class Utilities {

    // returns a char array containing every nth char
    // when sourceArray < n, returns sourceArray
    public char[] everyNthChar(char[] sourceArray, int n) {

        if (sourceArray == null || sourceArray.length < n) {
            return  sourceArray;
        }

        int returnLength = sourceArray.length / n;
        char[] result = new char[returnLength];
        int index = 0;

        for (int i = n-1; i < sourceArray.length; i += n) {
            result[index++] = sourceArray[i];
        }

        return result;
    }

    // Removes pairs of the same character that are next
    // to each other, by removing on e occurrence of the character
    // "ABBCDEEF" -> "ABCDEF"
    // "ABCDEEF" -> "ABCDEF"
    // the two B's aren't next to each other, so they aren't removed
    public String removePairs(String source) {

        // If length is less than  2, there won't be any pairs
        if (source == null  || source.length() <2) {
            return source;
        }

        StringBuilder sb = new StringBuilder();
        char[] string = source.toCharArray();

        for(int i=0; i < string.length - 1; i++) {
            System.out.println(string[i]);
            if(string[i] != string[i + 1]) {
                sb.append(string[i]);
            }
        }
        System.out.println(string[string.length -1]);
        // Add the final character, which is always safe
        sb.append(string[string.length - 1]);

        return sb.toString();
    }

    //performs a conversion based on some internal
    // business rule
    public int converter(int a, int b) {
        return (a/b) + (a * 30) - 2;
    }

    public String nullIfOddLength(String source) {
        if (source.length() % 2 == 0) {
            return source;
        }

        return null;
     }
}
