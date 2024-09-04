import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final int ARRAY_LENGHT = 500;

    public static void main(String[] args) {
        try{
            run();
        }catch (Exception e){
            System.out.printf("Ocurrió un error %s", e.getMessage());
        }
    }

    private static void run() {
        int[] unsortedArray = createArray();
        Arrays.stream(unsortedArray).forEach(number -> System.out.print(number + " "));
        System.out.println();

        //INSERCIÓN DIRECTA
        long startTime = System.nanoTime();
        int[] sortedArray = sortByDirectInsertion(unsortedArray);
        double endTime = (System.nanoTime() - startTime) / 1000000.0;

        System.out.printf("El algoritmo de insersión directa tardo %fms en ordenar el vector%n", (endTime));
        System.out.println("El array ordenado por ID de menor a mayor es:");
        Arrays.stream(sortedArray).forEach(number -> System.out.print(number + " "));


        //BURBUJA
        unsortedArray = createArray();
        Arrays.stream(unsortedArray).forEach(number -> System.out.print(number + " "));
        long startTime2 = System.nanoTime();
        int[] sortedArray2 = sortByBubble(unsortedArray);
        double endTime2 = (System.nanoTime() - startTime2) / 1000000.0;

        System.out.println();
        System.out.printf("El algoritmo de burbuja tardo %fms en ordenar el vector%n", (endTime2));
        System.out.println("El array ordenado por burbuja de menor a mayor es:");
        Arrays.stream(sortedArray2).forEach(number -> System.out.print(number + " "));
    }

    private static int[] sortByBubble(int[] unsortedArray) {
        int[] sortedArray = unsortedArray.clone();
        for(int i = 0; i < unsortedArray.length; i++){
            for(int j = i+1; j < unsortedArray.length; j++){
               if(sortedArray[i] > sortedArray[j]){
                   int aux = sortedArray[i];
                   sortedArray[i] = sortedArray[j];
                   sortedArray[j] = aux;
               }
            }
        }
        return sortedArray;
    }

    private static int[] sortByDirectInsertion(int[] unsortedArray) {
        int[] sortedArray = unsortedArray.clone();
        for(int i = 1; i < sortedArray.length; i++){
            int key = sortedArray[i];
            int j = i - 1;

            while (j >= 0 && sortedArray[j] > key) {
                sortedArray[j + 1] = sortedArray[j];
                j = j - 1;
            }
            sortedArray[j + 1] = key;
        }
        return sortedArray;
    }

    private static int[] createArray() {
       Random random = new Random();
       int[] array = new int[ARRAY_LENGHT];
       for (int i = 0; i < ARRAY_LENGHT; i++) {
          array[i] = random.nextInt(0, ARRAY_LENGHT);
       }
       return array;
    }
}