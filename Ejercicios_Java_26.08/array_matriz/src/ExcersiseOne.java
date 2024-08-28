import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExcersiseOne {

    ExcersiseOne(){
        int[] array = new int[100];
        for(int i = 0; i < 100; i++){
            array[i] = (int) ((Math.random() + 0.1) * 500);
        }
        System.out.println("First Array: " + Arrays.toString(array));
        exercise1(array);
    }

    public static void exercise1(int[] array){
        int[][] firstMatrix = firstOneHundred(array);
        int[][] finalMatrix = new int[35][11];
        finalMatrix = fillFinalMatrix(finalMatrix, firstMatrix, 0, 0);
        //System.out.println("First Matrix: " + Arrays.deepToString(firstMatrix));
        //System.out.println("Final Matrix: " +  Arrays.deepToString(finalMatrix));

        int[][] secondMatrix = nextFifty(array);
        Map<String, Integer> startColumnAndRowForSecond = getStartColumnAndRow(finalMatrix);
        finalMatrix = fillFinalMatrix(finalMatrix, secondMatrix, startColumnAndRowForSecond.get("row"), startColumnAndRowForSecond.get("col"));
        //System.out.println("Second Matrix: " + Arrays.deepToString(secondMatrix));
        //System.out.println("Final Matrix: " + Arrays.deepToString(finalMatrix));

        Map<String, Integer> startColumnAndRowForThird = getStartColumnAndRow(finalMatrix);
        int row = startColumnAndRowForThird.get("row") - 1;
        int column =startColumnAndRowForThird.get("col") - 1;
        int[][] thirdMatrix = last150(finalMatrix, row, column);
        Map<String, Integer> startColumnAndRowForFinal = getStartColumnAndRow(finalMatrix);
        finalMatrix = fillFinalMatrix(finalMatrix, thirdMatrix, startColumnAndRowForFinal.get("row"), startColumnAndRowForFinal.get("col"));
        //System.out.println("Third Matrix: " + Arrays.deepToString(thirdMatrix));
        System.out.println("Final Matrix: " + Arrays.deepToString(finalMatrix));
    }

    private static int[][] firstOneHundred(int[] array){
        int[][] matriz = new int[10][11];
        int count = 0;
        for (int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++){
                matriz[i][j] = array[count] * 3;
                count++;
                if(count == 100) break;
            }
        }
        return matriz;
    }

    private static int[][] nextFifty(int[] array){
        int[][] matrix = new int[5][11];
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 11; j++){
                matrix[i][j] = array[array.length - 1 - count] + array[array.length - count - 2];
                count += 2;
                if(count == 100) break;
            }
        }
        return matrix;
    }

    private static int[][] last150(int[][] matrix, int lastFilledRow, int lastFilledColumn){
        int[][] newMatrix = new int[15][11];
        for(int i = 0; i < newMatrix.length; i++){
            for(int j = 0; j < newMatrix[i].length; j++){
                if(lastFilledRow - 1 - i < 0) break;
                if(lastFilledColumn - j >= 0){
                    newMatrix[i][j] = matrix[lastFilledRow - i][lastFilledColumn - j];
                    continue;
                }
                newMatrix[i][j] = matrix[lastFilledRow - 1 - i][matrix[i].length - j];
            }
        }
        return newMatrix;
    }

    private static int[][] fillFinalMatrix(int[][] finalMatrix, int[][] partialMatrix, int startRow, int startCol){
        int[][] newFinalMatrix = finalMatrix.clone();
        int initValue = startCol;
        for(int i = 0; i < partialMatrix.length; i++){
            for(int j = initValue; j < partialMatrix[i].length; j++){
                if(initValue != 0){
                    newFinalMatrix[startRow + i][j] = partialMatrix[i][j - startCol];
                    initValue = 0;
                    continue;
                }
                newFinalMatrix[startRow + i][j] = partialMatrix[i][j];
            }
        }
        return newFinalMatrix;
    }

    private static Map<String, Integer> getStartColumnAndRow(int[][] matrix){
        Map<String, Integer> startColumnAndRow = new HashMap<>();
        boolean flag = false;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == 0){
                    startColumnAndRow.put("row", i);
                    startColumnAndRow.put("col", j);
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
        return startColumnAndRow;
    }
}
