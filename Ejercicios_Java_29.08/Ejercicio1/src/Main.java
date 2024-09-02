import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.text.BreakIterator;
import java.util.*;
/**
* Clase que contiene todas las funcionalidades del ejercicio 1 de la clase del 29/08
 * @author Ramiro Carnicer Souble
 * @version 30 de agosto de 2024
 */
public class Main {

    final static String NUMBER_FORMAT_EXCEPTION = "Solo puede ingresar numeros. Ingrese nuevamente";
    final static String INDEX_OUT_OF_BOUNDS_EXCEPTION = "La cantidad de argumentos no puede ser mayor a:";
    /**
     * Metodo que corre el programa
     * @throws IOException Tira esta exception cuando hay algún error con el buffer de entrada o salida
     * @param args argumentos del programa
     */
    public static void main(String[] args) throws IOException {
        try{
            run();
        } catch (Exception e){
            System.out.printf("Ocurrió un error en el programa: %s", e.getMessage());
        }
    }

    /**
     *  En este metodo se encuantra toda el manejo del flow del programa
     * @throws IOException Tira esta exception cuando hay algún error con el buffer de entrada o salida
     */
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
        Stack<Integer> evenStack = addEvenToStack(numbersList);

        System.out.println("La cola de numeros impares contiene: ");
        printList((List<Integer>) oddQueue);
        System.out.println("La pila de numeros pares contiene: ");
        printList(evenStack);
    }
    /**
     * Metodo que se utiliza leer el largo del array a ingresar
     * @return El tamaño del array a crear
     * @throws IOException Tira esta exception cuando hay algún error con el buffer de entrada o salida
     */
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

    /**
     * Agrega todos los numeros pares de la lista de numeros a un pila
     * @param numbersList Linked list con todos los numeros que ingreso el usuario
     * @return Una pila con todos los numeros pares de la list
     */
    private static Stack<Integer> addEvenToStack(LinkedList<Integer> numbersList) {
        Stack<Integer> oddStack = new Stack<>();
        numbersList.forEach(number -> {
            if(isEven(number)) oddStack.push(number);
        });
        return oddStack;
    }

    /**
     * Verifica si un numero es par o no
     * @param number numero que se quiere saber si es par o impar
     * @return Devuelve true si es par y false si no lo es
     */
    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Agrega todos los numeros impares de la lista de numeros a una cola
     * @param numbersList Linked list con todos los numeros que ingreso el usuario
     * @return Una cola con todos los numeros pares de la list
     */
    private static Queue<Integer> addOddToQueue(LinkedList<Integer> numbersList) {
        Queue<Integer> oddQueque = new LinkedList<>();
        numbersList.forEach(number -> {
            if(!isEven(number)) oddQueque.add(number);
        });
        return oddQueque;
    }

    /**
     * Agrega todos los valores del array que ingreso el usuario a una Linked List
     * @param numbers array con todos los numeros que ingreso el usuario
     * @return Una lista con todos los numeros que ingreso el usuario
     */
    private static LinkedList<Integer> convertToLinkedList(int[] numbers) {
        LinkedList<Integer> numbersList = new LinkedList<>();
        for (int number : numbers) {
            numbersList.add(number);
        }
        return numbersList;
    }

    /**
     * Verifica la cantidad de numeros narcicistas dentro del array que ingreso el usuario
     * @param numbers array con todos los numeros que ingreso el usuario
     * @return La cantidad de numeros narcicistas dentro del array
     */
    private static int checkCantNarcicist(int[] numbers) {
        int cantNarcicist = 0;
        for(int i = 0; i < numbers.length; i++){
           if(!isNarcissist(numbers[i]))
               continue;
           cantNarcicist++;
        }
        return cantNarcicist;
    }

    /**
     * Verifica si un numero es narcicista o no
     * @param number numero que se quiere verificar si es o no narcicista
     * @return true si el número es narcicista de lo contrario false
     */
    private static boolean isNarcissist(int number) {
        char[] stringNumber = Integer.toString(number).toCharArray();
        int result = 0;
        for(char digit : stringNumber){
            result += (int) Math.pow(Character.getNumericValue(digit), stringNumber.length);
        }
        return result == number;
    }

    /**
     * Lee los valores que ingresa el usuario y los convierte en un array
     * @param arraySize tamaño del que tiene que ser el array (ingresado por el usuario)
     * @return array que contiene los numeros que ingreso el usuario
     */
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

    /**
     * Imprime por consola todos los valores de una Lista
     * @param numbers Lista de numeros
     */
    private static void printList(List<Integer> numbers) {
        numbers.forEach(System.out::println);
    }

    /**
     * Imprime por consola todos los valores de una Array
     * @param array Array de numeros
     */
    private static void printArray(int[] array){
        System.out.println("El array contiene los siguiente elementos: ");
        Arrays.stream(array).forEach(System.out::println);
    }
}