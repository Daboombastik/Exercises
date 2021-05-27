import java.util.*;

public class Solution {

    public static void main(String[] args) {
        //Input: 20 6 hacker cup Output: Case #1: 3
        //Input: 100 20 hacker cup 2013 Output: Case #2: 10
        //Input: 10 20 MUST BE ABLE TO HACK Output: Case #3: 2
        //Input: 55 25 Can you hack Output: Case #4: 8
        //Input: 100 20 Hack your way to the cup Output: Case #5: 7

        System.out.println(solution(10, 20, "MUST BE ABLE TO HACK"));
    }

    public static String solution(int width, int height, String str) {
        int nbLines = 1;
        int nbSymbolsPerLine = width / (height / nbLines);
        StringBuilder result = new StringBuilder("Case #: ");
        //optimistic scenario - all the phrase fits the billboard
        //in that case maxFontSize == height
        if (nbSymbolsPerLine >= str.length()) {
            return result.append(height).toString();
        }

        //split the string by separate words
        String[] words = str.split("\\s");
        //get the length of the longest word - optional
        //can useful in situations where one word is much longer than the others
        int longestWord = Arrays.stream(words).max((o1, o2) ->
                Math.max(o1.length(), o2.length())).get().length();

        //until all the words fit the billboard we increment the number of lines
        while (true) {
            nbLines++;
            nbSymbolsPerLine = width / (height / nbLines);

            if (nbSymbolsPerLine >= longestWord) {
                if (checkLines(words, height, nbSymbolsPerLine, nbLines)) {
                    return result.append(height / nbLines).toString();
                }
            }
        }
    }

    private static boolean checkLines(String[] words, int height, int nbSymbolsPerLine, int nbLines) {
        int temp = 0;
        int lineCounter = 1;
        //check if all the words can fit the nbLines
        //if not go back to while loop in solution()
        for (int i = 0; i < words.length; i++) {
            //if the current word fits, add the next one
            // NB.here comparison <= is not suitable because the last word in line has no " "
            if (temp < nbSymbolsPerLine) {
                temp += words[i].length() + 1;
            } else {
            //if the line can no more words go to the next line (keeping the track of the current word)
                temp = 0;
                lineCounter++;
                i--;
            }
        }
        return lineCounter <= nbLines;
    }

}