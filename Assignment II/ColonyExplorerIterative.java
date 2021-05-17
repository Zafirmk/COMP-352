//-------------------------------------------------------
// Assignment 2
// Programming Question: Version 2
// Written by: Marwa Khalid (40155098) and Zafir Khalid (40152164)
//-------------------------------------------------------
// Purpose: This program consists of a iterative ExploreAndLabelColony method.

// Firstly, it generates a grid with rows and columns in the range
// of 5-20 filled randomly with 0s and 1s.

// It then takes the generated grid and uses it as one of the
// parameter for the ExploreAndLabelColony method. The method then
// starts looping through the grid starting at the row and column
// given. Every 1 that touches one another, in other words, the 
// coordinates of 1 colony is added into another array.

// The method then extracts each coordinates one by one from the
// array and that same coordinate in the grid is changed to the 
// respective label.

// Throughout this process, the generated grid, the grid
// after the colonies have formed, and the colony size is written
// to a text file called IterativeLog.txt.

// The entire process is repeated until 20 grids have been
// completed.

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Marwa Khalid (40155098) and Zafir Khalid (40152164)
 * course COMP352
 * assignment # (2)
 *Due Date (March 07, 2021)
 */
public class ColonyExplorerIterative {
	static ArrayList<String> colonyCoordinates = new ArrayList<String>();

	/**
	 * Main Iterative Method.
	 * 
	 * @param grid  - The grid to create colonies on
	 * @param i     - The row number
	 * @param j     - The column number
	 * @param label - The label to use
	 */
	public static int ExploreAndLabelColony(char[][] grid, int i, int j, char label) {
		int colonySize = 0; // DECLARING COLONY SIZE TO BE 0
		String coordinate = Integer.toString(i) + "," + Integer.toString(j); // STORING THE FIRST COORDINATE
		colonyCoordinates.add(coordinate); // ADDING TO ARRAY
		int oldArraySize = colonyCoordinates.size(); // STORING THE ARRAY SIZE
		checkSurroundingElements(grid, i, j); // CHECKING IF IT HAS SURROUNDING 1S
		
		while (colonyCoordinates.size() != oldArraySize) { // WHILE IT NO LONGER HAS SURROUNDING 1S
			int temp = colonyCoordinates.size(); // STORING THE ARRAY SIZE
			
			// LOOP FROM NEW COORDINATE UNTIL THE END OF ARRAY
			for (int l = oldArraySize; l < temp; l++) {
				String coordinates = colonyCoordinates.get(l); // STORING THE COORDINATE
				String x = coordinates.substring(0, coordinates.indexOf(',')); // STORING ROW
				String y = coordinates.substring(coordinates.indexOf(',') + 1); // STORING COLUMN
				checkSurroundingElements(grid, Integer.parseInt(x), Integer.parseInt(y)); // CHECKING ITS SURROUNDING
			}
			oldArraySize = temp; // OLD ARRAY SIZE BECOMES THE NEW SIZE
		}
		
		// LOOP THROUGH THE ARRAY CONTAINING COORDINATES
		for (int l = 0; l < colonyCoordinates.size(); l++) {
			String coordinates = colonyCoordinates.get(l); // STORING THE COORDINATE
			int x = Integer.parseInt(coordinates.substring(0, coordinates.indexOf(','))); // STORING ROW
			int y = Integer.parseInt(coordinates.substring(coordinates.indexOf(',') + 1)); // STORING COLUMN
			grid[x][y] = label; // REPLACING WITH LABEL
		}
		colonySize = colonyCoordinates.size(); // COLONY SIZE BECOMES THE SIZE OF THE ARRAY CONTAINING THE TOUCHING COORDINATES
		colonyCoordinates.clear(); // CLEARING THE ARRAY FOR NEXT LABEL
		
		// RETURN THE SIZE OF THE CURRENT COLONY
		return colonySize;
	}

	/**
	 * Method to check the surrounding cells if the cells contain 1 it is replaced
	 * by the respective label.
	 * 
	 * @param grid  - The grid to create colonies on
	 * @param i     - The row number
	 * @param j     - The column number
	 */
	public static void checkSurroundingElements(char[][] grid, int i, int j) {

		// TOP LEFT CORNER
		if (i != 0 && j != 0 && grid[i - 1][j - 1] == '1') { // IF LOCATION HAS 1
			 String coord = Integer.toString(i - 1) + "," + Integer.toString(j - 1); // STORING THE COORDINATE
			 checkDuplicate(coord); // CALLING THE CHECKDUPLICATE METHOD
		}

		// TOP
		if (i != 0 && grid[i - 1][j] == '1') { // IF LOCATION HAS 1
			 String coord = Integer.toString(i-1) + "," + Integer.toString(j); // STORING THE COORDINATE
			 checkDuplicate(coord); // CALLING THE CHECKDUPLICATE METHOD
		}

		// TOP RIGHT CORNER
		if (i != 0 && j != (grid[i].length - 1) && grid[i - 1][j + 1] == '1') { // IF LOCATION HAS 1
			 String coord = Integer.toString(i - 1) + "," + Integer.toString(j + 1); // STORING THE COORDINATE
			 checkDuplicate(coord); // CALLING THE CHECKDUPLICATE METHOD
		}

		// LEFT
		if (j != 0 && grid[i][j - 1] == '1') { // IF LOCATION HAS 1
			 String coord = Integer.toString(i) + "," + Integer.toString(j - 1); // STORING THE COORDINATE
			 checkDuplicate(coord); // CALLING THE CHECKDUPLICATE METHOD
		}

		// RIGHT
		if (j != (grid[i].length - 1) && grid[i][j + 1] == '1') { // IF LOCATION HAS 1
			 String coord = Integer.toString(i) + "," + Integer.toString(j + 1); // STORING THE COORDINATE
			 checkDuplicate(coord); // CALLING THE CHECKDUPLICATE METHOD
		}

		// BOTTOM LEFT CORNER
		if (j != 0 && i != (grid.length - 1) && grid[i + 1][j - 1] == '1') { // IF LOCATION HAS 1
			 String coord = Integer.toString(i + 1) + "," + Integer.toString(j - 1); // STORING THE COORDINATE
			 checkDuplicate(coord); // CALLING THE CHECKDUPLICATE METHOD
		}

		// BELOW
		if (i != (grid.length - 1) && grid[i + 1][j] == '1') { // IF LOCATION HAS 1
			 String coord = Integer.toString(i + 1) + "," + Integer.toString(j); // STORING THE COORDINATE
			 checkDuplicate(coord); // CALLING THE CHECKDUPLICATE METHOD
		}

		// BOTTOM RIGHT CORNER
		if (i != (grid.length - 1) && j != (grid[i].length - 1) && grid[i + 1][j + 1] == '1') { // IF LOCATION HAS 1
			 String coord = Integer.toString(i + 1) + "," + Integer.toString(j + 1); // STORING THE COORDINATE
			 checkDuplicate(coord); // CALLING THE CHECKDUPLICATE METHOD
		}
	}

	/**
	 * @param coordinate - coordinate on the grid
	 */
	public static void checkDuplicate(String coordinate) {
		// BOOLEAN TO CHECK IF ARRAY ALREADY CONTAINS COORDINATE
		boolean foundOrNot = false; // SET TO FALSE
		
		// LOOP THROUGH ARRAY
		for (int i = 0; i < colonyCoordinates.size(); i++) { 
			if (colonyCoordinates.get(i).equals(coordinate)) { // IF SAME COORDINATE
				foundOrNot = true; // BOOLEAN BECOMES TRUE
				break; // BREAK OUT OF LOOP
			}
		}
		
		if (!foundOrNot) { // IF COORDINATE DOESNT ALREADY EXIST IN THE ARRAY
			colonyCoordinates.add(coordinate); // ADD THE COORDINATE TO THE ARRAY
		}
	}
	
	public static char[][] generateGrid() {
		Random r = new Random(); // CREATING A RANDOM OBJECT CALLED r
		int rows = r.nextInt((20 - 5) + 1) + 5; // GENERATING A ROW NUMBER 5 AND 20 INCLUSIVELY
		int col = r.nextInt((20 - 5) + 1) + 5; // GENERATING A COLUMN NUMBER 5 AND 20 INCLUSIVELY
		String gridValues = "01"; // STRING CONTAINS 01
		char[][] grid = new char[rows][col]; // CREATING A GRID OF RANDOM ROW AND COLUMN SIZE

		// LOOPING THROUGH THE EMPTY ARRAY
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				// ADDING RANDOMLY CHOSEN 0 OR 1 AT EACH INDEX
				grid[i][j] = gridValues.charAt(r.nextInt(gridValues.length()));
			}
		}
		return grid; // RETURNING THE GENERATED GRID
	}
	
	/**
	 * @param args - an array of command-line arguments for the application
	 * @throws FileNotFoundException - if file is not found exception is thrown
	 */
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Welcome to Marwa and Zafir's Colony Explorer Iterative method!\n");
		String labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // STORING THE LABELS
		int index = 0; // INDEX FOR LOOPING THROUGH LABELS
		char[][] array; // ARRAY FOR THE GRID
		PrintWriter writer = new PrintWriter("IterativeLog.txt"); // CREATING A PRINT WRITER TO WRITE TO FILE	

		for (int k = 1; k < 21; k++) { // LOOP UNTIL 20 GRIDS
			System.out.println("Now processing Test #" + k);
			array = generateGrid(); // ARRAY BECOMES THE RETURNED ARRAY OF THE GENERATEGRID METHOD
			index = 0; // INDEX BECOMES TO 0
			
			// WRITING TO FILE
			writer.println("\n" + "~~~~~~~~~~ Test Number #" + (k) + "~~~~~~~~~~");
			writer.println("\n" + "BEFORE: ");
			writer.println(Arrays.deepToString(array).replace("],", "]\n")); // WRITING THE GENERATED GRID TO FILE
			writer.println("\nColonies:");

			// LOOP THROUGH THE GRID TO FORM COLONIES
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[i].length; j++) {
					if (array[i][j] == '1') { // IF THE LOCATION HAS 1
						// CALLING THE METHOD AND PRINTING THE COLONY SIZE
						writer.println(labels.charAt(index) + ": " + ExploreAndLabelColony(array, i, j, labels.charAt(index)));
						index++; // MOVING TO NEXT LABEL
						if (index == labels.length() - 1) { // IF ALL LABELS HAVE BEEN USED UP
							index = 0; // MOVING BACK TO FIRST LABEL 'A'
						}
					}
				}
			}

			// LOOP THROUGH THE GRID TO REPLACE ALL ZEROS
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[i].length; j++) {
					if (array[i][j] == '0') { // IF THE LOCATION HAS 0
						array[i][j] = '-'; // REPLACING 0 WITH DASH
					}
				}
			}
			
			// WRITING TO FILE
			writer.println("\n" + "AFTER: ");
			writer.println(Arrays.deepToString(array).replace("],", "]\n")); // PRINTING THE GRID WITH COLONIES AND DASHES
		}
		
		System.out.println("\nThank you for using Colony Explorer!");
		writer.close(); // CLOSING WRITER
	}
}