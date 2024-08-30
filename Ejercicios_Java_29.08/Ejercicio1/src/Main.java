import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.text.BreakIterator;
import java.util.*;

public class Main {

    final static String NUMBER_FORMAT_EXCEPTION = "Solo puede ingresar numeros. Ingrese nuevamente";
    final static String INDEX_OUT_OF_BOUNDS_EXCEPTION = "La cantidad de argumentos no puede ser mayor a:";

    public static void main(String[] args) throws IOException {
        try{
            run();
        } catch (Exception e){
            System.out.printf("Ocurrió un error en el programa: %s", e.getMessage());
        }
    }

    private static void run() throws IOException {
        System.out.println("Ingrese el tamaño del vector");
        int arraySize = readSize();

        System.out.println("Ingrese los numeros que quieren que sean parte del vector");
        int[] numbers = readArray(arraySize);

        printArray(numbers);

        int cantNarcicist = checkCantNarcicist(numbers);
        System.out.printf("Ingresaste %d numeros narcicistas%n", cantNarcicist);

        LinkedList<Integer> numbersList = convertToLinkedList(numbers);
        System.out.printf("El size del Linked list es: %d%n", numbersList.size());

        Queue<Integer> oddQueue = addOddToQueue(numbersList);
        Stack<Integer> evenStack = addEvenToQueue(numbersList);

        System.out.println("La cola de numeros impares contiene: ");
        printList((List<Integer>) oddQueue);
        System.out.println("La pila de numeros pares contiene: ");
        printList(evenStack);
    }

    private static int readSize() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_FORMAT_EXCEPTION);
            readSize();
        }
        return 0;
    }

    private static Stack<Integer> addEvenToQueue(LinkedList<Integer> numbersList) {
        Stack<Integer> oddStack = new Stack<>();
        numbersList.forEach(number -> {
            if(isEven(number)) oddStack.push(number);
        });
        return oddStack;
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static Queue<Integer> addOddToQueue(LinkedList<Integer> numbersList) {
        Queue<Integer> oddQueque = new LinkedList<>();
        numbersList.forEach(number -> {
            if(!isEven(number)) oddQueque.add(number);
        });
        return oddQueque;
    }

    private static LinkedList<Integer> convertToLinkedList(int[] numbers) {
        LinkedList<Integer> numbersList = new LinkedList<>();
        for (int number : numbers) {
            numbersList.add(number);
        }
        return numbersList;
    }

    private static int checkCantNarcicist(int[] numbers) {
        int cantNarcicist = 0;
        for(int i = 0; i < numbers.length; i++){
           if(!isNarcissist(numbers[i]))
               continue;
           cantNarcicist++;
        }
        return cantNarcicist;
    }

    private static boolean isNarcissist(int number) {
        char[] stringNumber = Integer.toString(number).toCharArray();
        int result = 0;
        for(char digit : stringNumber){
            result += (int) Math.pow(Character.getNumericValue(digit), stringNumber.length);
        }
        return result == number;
    }

    private static int[] readArray(int arraySize) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = new int[arraySize];
        String stringNumbers = sc.readLine();

        BreakIterator bi = BreakIterator.getWordInstance();
        bi.setText(stringNumbers);

        int start = bi.first();
        int counter = 0;
        try{
            for(int end = start; end != BreakIterator.DONE; start = end, end = bi.next()) {
                String word = stringNumbers.substring(start, end);
                if(word.isBlank()) continue;
                numbers[counter] = Integer.parseInt(word);
                counter ++;
            }
        }catch (NumberFormatException e){
            System.out.println(NUMBER_FORMAT_EXCEPTION);
            return readArray(arraySize);
        }catch (IndexOutOfBoundsException e){
            System.out.printf("%s %d y usted ingreso: %d", INDEX_OUT_OF_BOUNDS_EXCEPTION, arraySize, counter + 1);
            return readArray(arraySize);
        }
        sc.close();
        return numbers;
    }

    private static void printList(List<Integer> numbers) {
        numbers.forEach(System.out::println);
    }

    private static void printArray(int[] array){
        System.out.println("El array contiene los siguiente elementos: ");
        Arrays.stream(array).forEach(System.out::println);
    }
}