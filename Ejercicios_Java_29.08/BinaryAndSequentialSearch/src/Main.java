import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static private final int VECTOR_SIZE = 10000;

    public static void main(String[] args) {
        try{
            int[] array = initializeArray();
            Random random = new Random();
            int numberToSearch = array[random.nextInt((array.length))];
            run(array, numberToSearch);
        } catch (IOException e) {
            System.out.printf("Ocurrió un problema leyendo la consola: %s%n", e.getMessage());
        }catch (Exception e){
            System.out.printf("Ocurrió un problema corriendo el programa: %s%n", e.getMessage());
        }
        //tryScanner();
    }

    private static int[] initializeArray(){
       int[] array = new int[VECTOR_SIZE];
       for(int i = 0; i < array.length; i++){
          array[i] = (int) (Math.random() * 100);
       }
       return array;
    }

    private static void run(int[] array, int numberToSearch) throws IOException {
        System.out.printf("Ingrese que algoritmo de busqueda quiere utilizar %n [ 1 ] Secuencial %n [ 2 ] Binario %n");
        int choise = readNumber();
        /*
        System.out.println("Ingrese el numero que quiere buscar dentro del array");
        int numberToSearch = readNumber();
        */
        long startTime, endTime;
        switch (choise){
            case 1:
                startTime = System.nanoTime();
                doSequentialSearch(array, numberToSearch);
                endTime = System.nanoTime() - startTime;
                System.out.printf("La busqueda secuencial tardo: %f ms en ejecutarse%n", (endTime/1000000.0));
                break;
            case 2:
                startTime = System.nanoTime();
                doBinarySearch(array, numberToSearch);
                endTime = System.nanoTime() - startTime;
                System.out.printf("La busqeuda binaria tardo: %f ms en ejecutarse%n", (endTime/1000000.0));
                break;
            default:
                System.out.println("Error tiene que ingresar alguno de las opciones provistas");
                run(array, numberToSearch);
        }
        run(array, numberToSearch);
    }

    private static void tryScanner(){
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(" ");
        while(scanner.hasNext()){
            System.out.println(scanner.next());
        }
        scanner.close();
    }

    private static void doBinarySearch(int[] array, int numberToSearch){
        int[] sortedArray = Arrays.stream(array).sorted().toArray();
        int start = 0, end = array.length - 1;

        while(start <= end){
            int middle = start + (end - start) / 2;
            if(array[middle] == numberToSearch){
                return;
            }
            if(numberToSearch >= sortedArray[middle]){
                start = middle + 1;
            }else{
                end = middle - 1;
            }
        }
    }

    private static void doSequentialSearch(int[] array, int numberToSearch){
        Arrays.stream(array).filter(number -> number == numberToSearch).findFirst();
    }

    private static int readNumber() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Character.getNumericValue(br.read());
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

}