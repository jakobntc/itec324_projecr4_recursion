import java.util.Scanner;

/**
 * @Author Jakob Nathaniel Tyler Clapsaddle
 * Project 4
 * ITEC 324
 * Dr. Lahn
 *
 * Counting the number of landmasses and the number of lakes in a given 2D matrix.
 * Land is denoted as '*'
 * Water is denoted as '.'
 */
public class Main {

    public static void main(String[] args) {
        int c = 0;
        int r = 0;

        Scanner scan = new Scanner(System.in);

        String rowAndColumnIntString = scan.nextLine(); // Reading in the first line of the input.

        String[] rowAndColumnInts = rowAndColumnIntString.split(" "); // Splitting integers into an array

        String matrixString;

        // Creating two 2D Arrays for checking the number of landmasses as well as the number of lakes
        char[][] island2DArray = new char[Integer.parseInt(rowAndColumnInts[0])][Integer.parseInt(rowAndColumnInts[1])];
        char[][] lake2DArray = new char[Integer.parseInt(rowAndColumnInts[0])][Integer.parseInt(rowAndColumnInts[1])];

        // Populating the 2D Arrays
        for(int i = 0; i < Integer.parseInt(rowAndColumnInts[0]); i++) {
                matrixString = scan.nextLine();
                for(int j = 0; j < Integer.parseInt(rowAndColumnInts[1]); j++) {
                    island2DArray[i][j] = matrixString.charAt(j);
                    lake2DArray[i][j] = matrixString.charAt(j);
                }
        }

        // Calling the two methods that iterate through the arrays and search for either a
        // land symbol or a water symbol.
        int numIslands = landmasses(island2DArray);
        int numLakes = lakes(lake2DArray) - 1;

        System.out.println("The number of landmasses was: " + numIslands);
        System.out.println("The number of lakes was: " + numLakes);
    }

    /**
     * Iterates through the provided 2D Array and calls a recursive helper method when a '*'
     * symbol is encountered.
     *
     * @param land2DArray The 2D Array containing the matrix of land / water.
     * @return returns the number of landmasses contained in the 2D array.
     */
   public static int landmasses(char[][] land2DArray) {
        if(land2DArray == null || land2DArray.length == 0){
            return 0; // Returning 0 if the 2D array was null or empty.
        }

        int numIslands = 0;

        // Searching for a '*' character in the matrix
        for (int i = 0; i < land2DArray.length; i++) {
            for (int j = 0; j < land2DArray[i].length; j++) {
                if(land2DArray[i][j] == '*') {
                    // Calling a recursive method that returns 1 if a landmass is found.
                    numIslands += islandCounter(land2DArray, i, j);
                }
            }
        }
        return numIslands;
   }

    /**
     * Iterates through the provided 2D Array and calls a recursive helper method when a '.'
     * symbol is encountered.
     *
     * @param lake2DArray The 2D Array containing the matrix of land / water.
     * @return returns the number of lakes contained in the 2D array.
     */
   public static int lakes(char[][] lake2DArray){
        if(lake2DArray == null || lake2DArray.length == 0){
            return 0;
        }

        int numLakes = 0;

        for(int i = 0; i < lake2DArray.length; i++){
            for(int j = 0; j < lake2DArray[i].length; j++){
                if(lake2DArray[i][j] == '.'){
                    numLakes += lakeCounter(lake2DArray, i, j);
                }
            }
        }
        return numLakes;
   }

    /**
     * Recursively checks the spaces above, bellow, right, and left of a given point in a matrix.
     *
     * @param land2DArray The 2D array.
     * @param rowIndex The row index that the '*' character was found at.
     * @param columnIndex The column index that the '*' character was found at.
     * @return Returns 0 if rowIndex or columnIndex value is out of the range of the matrix, or the symbol was a '.' character.
     *         Returns 1 if the rowIndex or columnIndex value was in range of the matrix and the symbol was not a '.' character.
     */
   public static int islandCounter(char[][] land2DArray, int rowIndex, int columnIndex) {
        // Checking to make sure the values being passed in are within bounds of the matrix.
        if(
                rowIndex < 0 ||
                rowIndex >= land2DArray.length ||
                columnIndex < 0 ||
                columnIndex >= land2DArray[rowIndex].length ||
                land2DArray[rowIndex][columnIndex] == '.'
        ) {
            return 0;
        }

        land2DArray[rowIndex][columnIndex] = '.'; // "Deleting" the land character to make sure the recursive call doesn't repeat.

        // Recursively checking the spaces above, below, to the right, and to the left of the current space.
        islandCounter(land2DArray, rowIndex + 1, columnIndex);
        islandCounter(land2DArray, rowIndex - 1, columnIndex);
        islandCounter(land2DArray, rowIndex, columnIndex + 1);
        islandCounter(land2DArray, rowIndex, columnIndex - 1);
        return 1;
   }

    /**
     * Recursively checks the spaces above, bellow, right, and left of a given point in a matrix.
     *
     * @param lake2DArray The 2D array.
     * @param rowIndex The row index that the '.' character was found at.
     * @param columnIndex The column index that the '.' character was found at.
     * @return Returns 0 if rowIndex or columnIndex value is out of the range of the matrix, or the symbol was a '*' character.
     *         Returns 1 if the rowIndex or columnIndex value was in range of the matrix and the symbol was not a '*' character.
     */
   public static int lakeCounter(char[][] lake2DArray, int rowIndex, int columnIndex) {
        // Checking if the values passed in are within bounds of the matrix.
        if(
                rowIndex < 0 ||
                rowIndex >= lake2DArray.length ||
                columnIndex < 0 ||
                columnIndex >= lake2DArray[rowIndex].length ||
                lake2DArray[rowIndex][columnIndex] == '*'
        ) {
            return 0;
        }

        lake2DArray[rowIndex][columnIndex] = '*'; // "Deleting" the water character to make sure the recursive call doesn't repeat.

        // Recursively checking the spaces above, below, to the right, and to the left of the current space.
        lakeCounter(lake2DArray, rowIndex + 1, columnIndex);
        lakeCounter(lake2DArray, rowIndex - 1, columnIndex);
        lakeCounter(lake2DArray, rowIndex, columnIndex + 1);
        lakeCounter(lake2DArray, rowIndex, columnIndex - 1);
        return 1;
   }

}
