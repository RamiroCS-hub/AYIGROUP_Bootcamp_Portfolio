import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.SocketHandler;

public class Main {
    public static void main(String[] args) {
        try{
            run();
        } catch (Exception e) {
            System.out.printf("Ocurrió un error en el programa %s%n", e.getMessage());
        }
    }

    private static void run() {
        System.out.println("Ingrese el largo del array");
        int size = readNumber();

        //System.out.println("Ingrese los datos del array");
        System.out.println("Creando array..");
        //int[] array = readArray(size);
        int[] array = getArray(size);

        System.out.println("El array contiene: ");
        Arrays.stream(array).forEach(System.out::println);

        int[] sortedArray = sortArray(array);
        System.out.println("El array ordenado de menor a mayor es: ");
        Arrays.stream(sortedArray).forEach(System.out::println);

        LinkedList<Integer> arrayList = cloneArrayToList(sortedArray);
        sortedArray = Arrays.stream(sortedArray).map(number -> number = 0).toArray().clone();
        Arrays.stream(sortedArray).forEach(System.out::println);

        System.out.println("La linked list es: ");
        arrayList.forEach(System.out::println);
        arrayList = (LinkedList<Integer>) deleteRepeatedNumbers(arrayList).clone();

        System.out.println("La linked list sin valores repetidos es: ");
        arrayList.forEach(System.out::println);

        int cantNarcicist = getCantNarcicist(arrayList);
        System.out.println("La cantidad de numeros narcicist en el array es: " + cantNarcicist);

        System.out.println("Los numeros de Keprekar en la lista son: ");
        showKeprekarNumbers(arrayList);
    }

    private static void showKeprekarNumbers(LinkedList<Integer> arrayList) {
        for (int i : arrayList) {
            if(isKeprekar(i))
                System.out.println(i);
        }
    }

    private static boolean isKeprekar(int number) {
        int[] numberArray = fromIntToIntArray(number);
        int numberKeprekared = getKeprekarNumber(numberArray);
        return numberKeprekared == number;
    }

    private static int getKeprekarNumber(int[] numberArray) {
        int sum = 0;
        for(int i : numberArray){
           sum += (int) Math.pow(i, 2);
        }
        return sum;
    }

    private static int getCantNarcicist(LinkedList<Integer> arrayList) {
        int counter = 0;
        for(int i : arrayList) {
           if(isNarcicist(i))
               counter++;
        }
        return counter;
    }

    private static boolean isNarcicist(int n) {
        int[] numberArray = fromIntToIntArray(n);
        int numberNarcicisted = getNarcicist(numberArray);
        return numberNarcicisted == n;
    }

    private static int[] fromIntToIntArray(int n) {
        char[] number = getCharArray(n);
        return charArrayToInt(number);
    }

    private static char[] getCharArray(int n){
        String nString = String.valueOf(n);
        char[] number = nString.toCharArray();
        return number;
    }

    private static int getNarcicist(int[] numberArray) {
        int sum = 0;
        for(int i : numberArray) {
           sum += (int) Math.pow(i, numberArray.length);
        }
        return sum;
    }

    private static int[] charArrayToInt(char[] number){
        int[] numberArray = new int[number.length];
        for (int h = 0; h < number.length; h++) {
            numberArray[h] = getIntFromChar(number[h]);
        }
        return numberArray;
    }

    private static int getIntFromChar(char d){
       return Integer.parseInt(String.valueOf(d));
    }

    private static LinkedList<Integer> deleteRepeatedNumbers(LinkedList<Integer> arrayList) {
        LinkedList<Integer> newList = (LinkedList<Integer>) arrayList.clone();
        for(int i : arrayList) {
           for(int j = 1; j < arrayList.size(); j++) {
               if(arrayList.indexOf(i) == j)
                   continue;

               if(i == arrayList.get(j)) {
                   newList.remove(arrayList.indexOf(i));
               }
           }
        }
        return newList;
    }

    private static LinkedList<Integer> cloneArrayToList(int[] array) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    private static int[] getArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for(int i=0; i<size; i++){
           int number = (random.nextInt(1, 10));
           if(verifyNumber(Integer.toString(number))){
               i--;
               continue;
            }
           array[i] = number;
        }
        return array;
    }

    private static int[] sortArray(int[] array) {
        int[] sortedArray = array.clone();
        for(int i = 0; i < sortedArray.length; i++){
            for(int j = i+1; j < sortedArray.length; j++){
                if(sortedArray[i] > sortedArray[j]){
                    int number = sortedArray[i];
                    sortedArray[i] = sortedArray[j];
                    sortedArray[j] = number;
                }
            }
        }
        return sortedArray;
    }

    private static int[] readArray(int size) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(" ");
        int[] array = new int[size];

        try {
            System.out.printf("Introduce %d numeros%n", size);
            for(int i = 0; i < size; i++) {
                //array[i] = verifyNumber(sc.next());
                System.out.println(sc.next());
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return readArray(size);
        }

        sc.close();

        return array;
    }

    private static boolean verifyNumber(String number) {
        int numberInt = Integer.parseInt(number);
        return numberInt < 0;
    }

    private static int readNumber() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        return size;
    }
}