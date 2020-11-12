import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int c = 0;
        int r = 0;

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the amount of rows contained in the 2d array.");
        r = scan.nextInt();
        System.out.println("Please enter the amount of columns contained in the 2d array.");
        c = scan.nextInt();
        scan.nextLine();
        String testing;

        char[][] island2DArray = new char[r][c];
        char[][] lake2DArray = new char[r][c];

        //
        for(int i = 0; i < r; i++) {
                testing = scan.nextLine();
                for(int j = 0; j < c; j++) {
                    island2DArray[i][j] = testing.charAt(j);
                    lake2DArray[i][j] = testing.charAt(j);
                }
        }

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                System.out.print(island2DArray[i][j]);
            }
            System.out.println();
        }

        int numIslands = islands(island2DArray);
        int numLakes = lakes(lake2DArray);
        System.out.println("The number of islands was: " + numIslands);
        System.out.println("The number of lakes was: " + numLakes);
    }

   public static int islands(char[][] grid) {

        if(grid == null || grid.length == 0){
            return 0;
        }

        int numIslands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '*') {
                    numIslands += islandCounter(grid, i, j);
                }
            }
        }

        return numIslands;

   }

   public static int lakes(char[][] grid){
        if(grid == null || grid.length == 0){
            return 0;
        }

        int numLakes = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == '.'){
                    numLakes += lakeCounter(grid, i, j);
                }
            }
        }
        return numLakes;
   }

   public static int islandCounter(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '.') {
            return 0;
        }

        grid[i][j] = '.';
        islandCounter(grid, i + 1, j);
        islandCounter(grid, i - 1, j);
        islandCounter(grid, i, j + 1);
        islandCounter(grid, i, j - 1);
        return 1;
   }

   public static int lakeCounter(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '*') {
            return 0;
        }

        grid[i][j] = '*';
        lakeCounter(grid, i + 1, j);
        lakeCounter(grid, i - 1, j);
        lakeCounter(grid, i, j + 1);
        lakeCounter(grid, i, j - 1);
        return 1;
   }

}
