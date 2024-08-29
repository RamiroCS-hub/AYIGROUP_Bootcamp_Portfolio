import java.io.Console;
import java.text.BreakIterator;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        console();
    }

    private static void console(){
        Console console = System.console();
        if (console == null) {
            return;
        }
        String input = console.readLine();
        HashMap<Integer, String> words = splitInputInWords(input);
        System.out.println("---- HASH MAP OF THE INPUT ----");
        showHashData(words);

        words = (HashMap<Integer, String>) deleteWordsShorterThanFive(words).clone();
        System.out.println("---- HASH MAP OF THE FILTERED INPUT ----");
        showHashData(words);

        LinkedList<String> inverseWords = getInverseWords(words);
        System.out.println("---- LINKED LIST OF INVERSED HASH FILTERED MAP ----");
        System.out.println(Arrays.deepToString(inverseWords.toArray()));

        String inverseWordsString = String.join(" ", inverseWords);
        System.out.println("---- STRING OF THE LINKED LIST ----");
        System.out.println(inverseWordsString);

        words.clear();
        System.out.println("--- HASH MAP CLEANED ---");
        inverseWords.clear();
        showHashData(words);
        System.out.println("--- LINKED LIST CLEANED ---");
        System.out.println(Arrays.deepToString(inverseWords.toArray()));
    }

    private static LinkedList<String> getInverseWords(HashMap<Integer, String> words){
//        Integer[] keys = words.keySet().toArray(new Integer[0]);
        List<Integer> keys = new ArrayList<>(words.keySet());

        LinkedList<String> inverseWords = new LinkedList<>();
        for(int i = keys.size() - 1; i >= 0; i--){
           inverseWords.add(words.get(keys.get(i)));
        }
        return inverseWords;
    }

    private static HashMap<Integer, String> deleteWordsShorterThanFive(HashMap<Integer, String> words){
        HashMap<Integer, String> wordShorter = new HashMap<>();
        for(Integer word : words.keySet()){
           if(words.get(word).length() >= 5)
               wordShorter.put(word, words.get(word));
        }
        return wordShorter;
    }

    private static void showHashData(HashMap<Integer, String> words){
        for(Integer word : words.keySet()){
            System.out.println(word + " " + words.get(word));
        }
    }

    private static HashMap<Integer, String> splitInputInWords(String input) {
        BreakIterator bi = BreakIterator.getWordInstance();
        bi.setText(input);
        int start = bi.first();
        HashMap<Integer, String> words = new HashMap<>();
        int counter = 0;
        for(int end = bi.next(); end != BreakIterator.DONE; start = end, end = bi.next()) {
            if(input.substring(start, end).equals(" ")) continue;
            words.put(counter, input.substring(start, end));
            counter++;
        }
        return words;
    }
}