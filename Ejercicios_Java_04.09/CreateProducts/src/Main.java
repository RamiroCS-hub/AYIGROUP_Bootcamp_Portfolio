import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Main {

    static final int VECTOR_SIZE = 10;
    static final ArrayList<String> NAMES = new ArrayList<String>(Arrays.asList("Manzana", "Banana", "Pera", "Palta", "Mandarina", "Naranja"));

    public static void main(String[] args) {
        try{
            run();
        } catch (Exception e){
            System.out.printf("Ocurrió un error en el programa: %s%n", e.getMessage());
        }
    }

    private static void run() {
        Product[] products = getProductArray();
        Arrays.stream(products).forEach(product -> System.out.printf("%s %s %d PLU: %d %f %n",
                product.getName(), product.getDescription(), product.getEanCode(), product.getPLUCode(),
                product.getPrice()));
        Product[] sortenedProducts = sortProductsByPLU(products);

        System.out.println("Productos ordenados por PLU:");
        Arrays.stream(sortenedProducts).forEach(product -> System.out.printf("%s %s %d PLU: %d %f %n",
                product.getName(), product.getDescription(), product.getEanCode(), product.getPLUCode(),
                product.getPrice()));
    }

    private static Product[] sortProductsByPLU(Product[] products) {
        Product[] sortedProducts = products.clone();
        for (int i = 0; i < sortedProducts.length - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < sortedProducts.length; j++) {
                if (sortedProducts[j].getPLUCode() < sortedProducts[indiceMenor].getPLUCode()) {
                    indiceMenor = j;
                }
            }
            // Intercambiar el elemento menor con el primer elemento no ordenado
            Product temp = sortedProducts[indiceMenor];
            sortedProducts[indiceMenor] = sortedProducts[i];
            sortedProducts[i] = temp;
        }
        return sortedProducts;
    }

    private static Product[] getProductArray(){
        Product[] products = new Product[VECTOR_SIZE];
        Random random = new Random();
        for(int i = 0; i < VECTOR_SIZE; i++){
            products[i] = new Product(random.nextInt(5000, 9999), random.nextInt(10000, 99999),
                    NAMES.get(random.nextInt(0, NAMES.size() - 1)), "Descripción de producto " + i, random.nextDouble(50, 500));
        }
        return products;
    }

}