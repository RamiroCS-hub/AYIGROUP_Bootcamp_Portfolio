import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try{
           run();
        } catch (IOException e) {
            System.out.printf("Ocurrió un problema leyendo la consola: %s%n", e.getMessage());
        }catch (Exception e){
            System.out.printf("Ocurrió un problema corriendo el programa: %s%n", e.getMessage());
        }
    }

    private static int[] initializeArray(){
       int[] array = new int[500];
       for(int i = 0; i < array.length; i++){
          array[i] = (int) (Math.random() * 100);
       }
       return array;
    }

    private static void run() throws IOException {
        int[] array = initializeArray();
        System.out.printf("Ingrese que algoritmo de busqueda quiere utilizar %n [ 1 ] Secuencial %n [ 2 ] Binario %n");
        int choise = readNumber();
        /*
        System.out.println("Ingrese el numero que quiere buscar dentro del array");
        int numberToSearch = readNumber();
        */
        Random random = new Random();
        int numberToSearch = array[random.nextInt((array.length))];
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
                run();
        }
        run();
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