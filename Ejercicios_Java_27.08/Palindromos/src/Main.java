import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;

public class Main {

    static int cantOfPalindromes = 0;
    static int cantOfNormalWords = 0;

    public static void main(String[] args) throws IOException {
       palindrome();
    }

    private static void palindrome() throws IOException {
        // Setting the input reader
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String input = buffer.readLine();
        ArrayList<String> words = returnArrayOfWords(input);
        LinkedList<String> palindromes = countCantOfPalindromes(words);

        System.out.println("Cantidad de palindromos: " + cantOfPalindromes);
        System.out.println("Cantidad de palabras normales: " + cantOfNormalWords);

        System.out.println(Arrays.deepToString(palindromes.toArray()));
        deletePalindromesFromArray(words, palindromes);

        System.out.println("El tamaño del ArrayList es: " +  cantOfNormalWords);
        words.removeAll(words);
        System.out.println("Se limpio el Array List");

        System.out.println("El tamaño del LinkedList es: " +  cantOfPalindromes);
        palindromes.removeAll(palindromes);
        System.out.println("Se limpio el Linked List");
    }


    private static void deletePalindromesFromArray(ArrayList<String> words, LinkedList<String> palindromes) {
        words.removeIf(palindromes::contains);
    }

    private static ArrayList<String> returnArrayOfWords(String input){
        //Setting de Break Itereator
        String trimmed = input.trim();
        BreakIterator wordIterator = BreakIterator.getWordInstance(Locale.US);
        wordIterator.setText(trimmed);

        int start = wordIterator.first(); //Mira donde empieza la primera palabra
        ArrayList<String> words = new ArrayList<>();
        for (int end = wordIterator.next(); end != BreakIterator.DONE; start = end, end = wordIterator.next()) {
            if(trimmed.substring(start, end).equals(" ")) continue;
            words.add(trimmed.substring(start, end).trim().toLowerCase());
        }
        return words;
    }

    private static LinkedList<String> countCantOfPalindromes(ArrayList<String> words){
        LinkedList<String> palindromes = new LinkedList<>();
        for(String word : words) {
            boolean flag = true;
            char[] arrayWord = word.toCharArray();
            for(int i = 0; i < word.length(); i++) {
                if(arrayWord[i] != arrayWord[arrayWord.length - 1 - i] || Character.isDigit(arrayWord[i])) {
                    flag = false;
                    cantOfNormalWords++;
                    break;
                }
            }
            if(flag){
                cantOfPalindromes++;
                palindromes.add(word);
            }
        }
        return palindromes;
    }
}