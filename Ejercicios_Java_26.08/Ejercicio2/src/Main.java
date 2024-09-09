import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Main {

    final static String INVALID_NUMBER_MESSAGE = "Ingreso mal el formato de numero romano";
    final static LinkedList<Integer> SYMBOLS_NUMBER = new LinkedList<>(List.of(10, 50, 100));

    public static void main(String[] args) {
        try{
            run();
        }catch (Exception e){
            System.out.printf("Ocurrió un error en el programa: %s %s",e.getMessage(), e.getCause());
        }
    }

    private static void run() throws IOException {
        System.out.println("Ingrese el numero romano");
        String romanNumber = readUserInput();
        char[] stringArray = getArrayString(romanNumber);
        validateInput(stringArray);
        int intNumber = parseRomanToInt(stringArray);
        System.out.println();
        System.out.printf("El convertido a decimal es: %d", intNumber);
    }

    private static int parseRomanToInt(char[] romanNumberArray){
        int sum = 0;
        LinkedList<Integer> intList = romanArrayToIntArray(romanNumberArray);
        validateList(intList);
        showArray(intList);
        for(int num : intList){
            sum += num;
        }
        if(romanNumberArray.length > 1 && SYMBOLS_NUMBER.contains(sum)){
            throw new InputMismatchException(INVALID_NUMBER_MESSAGE);
        }
        return sum;
    }

    private static void validateList(LinkedList<Integer> list){
        for (int i = 0; i < list.size(); i++) {
            if(i-1 >= 0 && list.get(i) > list.get(i-1)){
                throw new InputMismatchException(INVALID_NUMBER_MESSAGE);
            }
        }
    }

    private static void showArray(LinkedList<Integer> array){
       array.forEach(num -> System.out.print(num + " "));
    }

    private static LinkedList<Integer> romanArrayToIntArray(char[] romanNumberArray){
        LinkedList<Integer> intArray = new LinkedList<>();
        for(int i = 0; i < romanNumberArray.length; i++){
            System.out.println(romanNumberArray[i]);
           switch (romanNumberArray[i]){
               case 'I':
                   intArray.add(1);
                   break;
               case 'V':
                   if(i-1 >= 0 && romanNumberArray[i - 1] == 'I'){
                       intArray.removeLast();
                       intArray.add(4);
                   }else{
                       intArray.add(5);
                   }
                   break;
               case 'X':
                   if(i-1 >= 0 && romanNumberArray[i - 1] == 'I'){
                       intArray.removeLast();
                       intArray.add(9);
                   }else{
                       intArray.add(10);
                   }
                    break;
               case 'L':
                   if(i-1 >= 0 && romanNumberArray[i - 1] == 'X'){
                       intArray.removeLast();
                       intArray.add(40);
                   }else{
                       intArray.add(50);
                   }
                   break;
           }
        }
        return intArray;
    }

    private static void validateInput(char[] stringArray) {
        int counter = 0;
        for(int i = 0; i < stringArray.length-1; i++){
            if(i-1 >= 0 && stringArray[i] == stringArray[i-1]){
                counter++;
            } else {
                counter = 0;
            }
            if(counter > 2)
                throw new InputMismatchException(INVALID_NUMBER_MESSAGE);
        }
    }

    private static char[] getArrayString(String input) {
        return input.toCharArray();
    }

    private static String readUserInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        br.close();
        return input;
    }
}