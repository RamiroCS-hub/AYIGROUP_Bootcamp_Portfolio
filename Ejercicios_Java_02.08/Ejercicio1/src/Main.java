import java.io.IOException;
import java.util.*;
import java.util.logging.SocketHandler;

public class Main {
    public static void main(String[] args) {
        try{
            run();
        } catch (Exception e) {
            System.out.printf("Ocurrió en el programa %s%n", e.getMessage());
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
    }

    private static int[] getArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for(int i=0; i<size; i++){
           array[i] = (random.nextInt(1, 500));
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
                array[i] = verifyNumber(sc.next());
                System.out.println(sc.next());
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return readArray(size);
        }

        sc.close();

        return array;
    }

    private static int verifyNumber(String number) {
        int numberInt = Integer.parseInt(number);
        if(numberInt < 0){
            throw new IllegalArgumentException("ELos valores del array deben de ser mayores o iguales a 0");
        }
        return numberInt;
    }

    private static int readNumber() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        return size;
    }
}