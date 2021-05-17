//-------------------------------------------------------
// Assignment 2
// Programming Question: Version 1
// Written by: Zafir Khalid (40152164) and Marwa Khalid (40155098) 
//-------------------------------------------------------
// Purpose: This program consists of a recursive ExploreAndLabelColony method.

// Firstly, it generates a grid with rows and columns in the range
// of 5-20 filled randomly with 0s and 1s.

// It then takes the generated grid and uses it as one of the
// parameter for the ExploreAndLabelColony method. The method then
// starts looping through the grid recursively starting at the row and column
// given. For every 1 that is found surronding another 1, a recursive call
// is made. This process is repeated until a recursive tree is generated
// depicting the positions of every 1 in a single colony. Each 1 is then
// replaced using a label that is also passed as a parameter.

// For a single run of the recursive function, a single colony is marked.
// The function is called until no more 1's exist in the grid.
// A supplementary method are used to check if any 1s exist in the grid.

// This recursive method is "tail recursive" - the recursive call is made
// only at the end of the function. To do this, for each 1 found in the
// surrondings its X and Y coordinate get appended to an array list and
// then the recursive call is made for each X and Y coordinate in the list

// The entire process is repeated until 20 grids have been
// completed.

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Zafir Khalid (40152164) and Marwa Khalid (40155098)
 * course COMP352
 * assignment # (2)
 *Due Date (March 07, 2021)
 */

public class ColonyExplorerRecursiveTail {


    static char[][] grid = generateGrid();
    static int colonySize = 0;
    static int rows = grid.length;
    static int cols = grid[0].length;

    public static void ExploreAndLabelColonyTail(int x, int y, char[][] grid, char label, int index){

        if(grid[x][y] == '1'){
            grid[x][y] = label;
            colonySize++;
        }

        ArrayList<Integer> surrondings = new ArrayList<Integer>();


        while(index < (rows*cols)){

            // TOP LEFT CORNER
            if (x != 0 && y != 0 && grid[x - 1][y - 1] == '1') {
                surrondings.add(x-1);
                surrondings.add(y-1);
            }

            // TOP
            if (x != 0 && grid[x - 1][y] == '1') {
                surrondings.add(x-1);
                surrondings.add(y);
            }

            // TOP RIGHT CORNER
            if (x != 0 && y != (grid[x].length - 1) && grid[x - 1][y + 1] == '1') {
                surrondings.add(x-1);
                surrondings.add(y+1);
            }

            // RIGHT
            if (y != (grid[x].length - 1) && grid[x][y + 1] == '1') {
                surrondings.add(x);
                surrondings.add(y+1);
            }

            // BOTTOM RIGHT CORNER
            if (x != (grid.length - 1) && y != (grid[x].length - 1) && grid[x + 1][y + 1] == '1') {
                surrondings.add(x+1);
                surrondings.add(y+1);
            }

            // BOTTOM
            if (x != (grid.length - 1) && grid[x + 1][y] == '1') {
                surrondings.add(x+1);
                surrondings.add(y);
            }

            // BOTTOM LEFT CORNER
            if (y != 0 && x != (grid.length - 1) && grid[x + 1][y - 1] == '1') {
                surrondings.add(x+1);
                surrondings.add(y-1);
            }

            // LEFT
            if (y != 0 && grid[x][y - 1] == '1') {
                surrondings.add(x);
                surrondings.add(y-1);
            }

            index++;

            for(int k=0; k<((surrondings.size())/2); k=k+2){
                int newX = surrondings.get(k);
                int newY = surrondings.get(k+1);
                ExploreAndLabelColonyTail(newX, newY, grid, label, 0);
            }

        }



    }

    public static int[] locateIndex(char[][] grid) {

        //Get the number of rows and columns in the grid currently
        int rows = grid.length;
        int columns = grid[0].length;

        //x and y variables that will be returned in an array
        int i = 0;
        int j = 0;

        //Boolean to check if a 1 is found or not
        boolean breakTime = false;

        //Loop over the rows
        for (; i < rows; i++) {
            j = 0;
            //Loop over the columns
            for (; j < columns; j++) {
                //If a 1 is found set boolean to true and break
                if (grid[i][j] == '1') {
                    breakTime = true;
                    break;
                }
            }
            //Break for the outer loop
            if (breakTime) {
                break;
            }
        }
        //Create and return the array of size 2
        int[] toReturn = new int[2];
        toReturn[0] = i;
        toReturn[1] = j;
        return (toReturn);
    }

    public static char[][] generateGrid() {

        //Create the upper and lower bounds for random number generation
        String oneAndZero = "10";
        final int UPPER_BOUND = 20;
        final int LOWER_BOUND = 5;

        //Generate the number of rows and columns then create the array
        Random r = new Random();
        int rows = r.nextInt((UPPER_BOUND - LOWER_BOUND) + 1) + LOWER_BOUND;
        int cols = r.nextInt((UPPER_BOUND - LOWER_BOUND) + 1) + LOWER_BOUND;

        char[][] toReturn = new char[rows][cols];

        //Insert 1's and 0's into the grid
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                char toInsert = oneAndZero.charAt(r.nextInt(2));
                toReturn[x][y] = toInsert;
            }
        }

        //Return the array
        return (toReturn);

    }

    public static boolean oneExists(char[][] grid) {

        //Get the number of rows and columns in the grid currently
        int rows = grid.length;
        int columns = grid[0].length;

        //Initialize loop variables
        int i = 0;
        int j = 0;

        //Boolean to show that a 1 is found
        boolean breakTime = false;

        //Loop over rows
        for (; i < rows; i++) {
            j = 0;
            //Loop over columns
            for (; j < columns; j++) {
                //If a 1 is found, break
                if (grid[i][j] == '1') {
                    breakTime = true;
                    break;
                }
            }
            //Break outer loop
            if (breakTime) {
                break;
            }
        }

        //If a 1 is found return true, else false
        if (!breakTime) {
            return (false);
        } else {
            return (true);
        }
    }  

    public static void zeroReplacer(char[][] grid) {
        
        //Get the number of rows and columns in the grid currently
        int rows = grid.length;
        int columns = grid[0].length;

        //Initialize variables to loop over the grid
        int i = 0;
        int j = 0;

        //Loop over rows
        for (; i < rows; i++) {
            j = 0;
            //Loop over columns
            for (; j < columns; j++) {
                //If a 0 is found replace it with a -
                if (grid[i][j] == '0') {
                    grid[i][j] = '-';
                }
            }
        }
    }

    public static void main(String[] args) {


        //Create a print writer object to write the log file
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Tail-RecursiveLog.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error Occured");
        }

        //All the possible labels that can be used
        String allLabels = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        writer.println("---------- RECURSIVE TEST LOG ----------");
        System.out.println("Welcome to Marwa and Zafir's Colony Explorer Iterative method!\n");


        //Looping to create 20 different logs
        for(int i=0; i<=19; i++){

            System.out.println("Now processing Test #" + (i+1));

            //Index of the current label
            int index = 0;

            //Creating new grid
            grid = generateGrid();
            writer.println("\n" + "~~~~~~~~~~ Test Number #" + (i+1) + "~~~~~~~~~~");
            writer.println("\n"+"BEFORE: ");

            //Writing the grid to the log file
            writer.println(Arrays.deepToString(grid).replace("],", "]\n") + "\n");
            writer.println("Colony Sizes: ");

            //While a 1 still exists, the method is called recursively
            while(oneExists(grid)){

                //Finding the current index of the 1
                int indices[] = locateIndex(grid);
                
                //Recursive method call
                ExploreAndLabelColonyTail(indices[0], indices[1], grid, allLabels.charAt(index),0);

                //Output the colony size to the .txt file
                writer.println(allLabels.charAt(index) + ": " + colonySize);

                //Reset the static colony size variable
                colonySize = 0;

                //Go to next label
                index++;
                
                //Reset label back to start if all used up
                if(index == allLabels.length()-1){
                    index=0;
                }
            }

            //Replace all the 0's with -'s
            zeroReplacer(grid);

            writer.println("\n"+"AFTER: ");

            //Print array with all colonies created and 0's replaced
            writer.println(Arrays.deepToString(grid).replace("],", "]\n"));

        }
        

		System.out.println("\nThank you for using Colony Explorer!");
        
        //Close of writer
        writer.close();




    }



}
