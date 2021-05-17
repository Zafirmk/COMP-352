import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Recursive {

    /**
     * Function to generate a random char array with numOfHash #'s
     * @param numOfHash - The number of hashtags the char array should have
     * @return  - char array with random number of X's O's and #'s
     */
    public static char[] getRandomString(int numOfHash) {
        String chars = "XO";
        StringBuilder randomString = new StringBuilder();
        Random rnd = new Random();

        //Generate a string with only X's and O's
        while (randomString.length() < ((int) (Math.random() * 100) + numOfHash)) {
            int index = (int) (rnd.nextFloat() * chars.length());
            randomString.append(chars.charAt(index));
        }

        //Replace some characters with #'s
        for (int i = 1; i <= numOfHash; i++) {
            //Generate random index and replace character
            int index = (int) ((Math.random() * (randomString.length() - 1 - 0)) + 0);
            //Keep generating index until no more index collision
            while (randomString.charAt(index) == '#') {
                index = (int) ((Math.random() * (randomString.length() - 1 - 0)) + 0);
            }            randomString.setCharAt(index, '#');
        }

        //Convert string to array of chars and return
        char[] toReturn = new char[randomString.length()];
        randomString.getChars(0, randomString.length() - 1, toReturn, 0);
        return (toReturn);
    }

    /**
     * UnHide recursive method to find all permutations of the row
     * @param arr - Row containing X's O's and #'s
     * @param currIndex - Index where method begins checking for #'s
     * @param writer - Printwriter to output to the file
     */
    public static void UnHide(char[] arr, int currIndex, PrintWriter writer) {

        //Base case checking if array reached its start
        if (currIndex == -1){
            writer.println(arr);
            return;
        }
        //If current index is a # replace it with X's and O's
        if (arr[currIndex] == '#'){
            arr[currIndex] = 'O';
            //Call to Unhide to process next position in the char array after replacing with an O
            UnHide(arr, currIndex-1, writer);
            arr[currIndex] = 'X';
            //Call to Unhide to process next position in the char array after replacing with an X
            UnHide(arr, currIndex-1, writer);
            //Reset currIndex back to #
            arr[currIndex] = '#';
        }
        else{
            //If no # at current index proceed to next index
            UnHide(arr, currIndex-1, writer);
        }
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {

        //Initialize print writer
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("out(Recursive).txt", "UTF-8");
        } catch (FileNotFoundException e) {
            System.out.println("Error Occured");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error Occured");
        }


        //Call the UnHide function certain number of times
        for(int i = 1; i<=12; i++){
            writer.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            //Number of hashes is always even
            int numOfHash = i*2;
            char[] randomRow = getRandomString(numOfHash);
            writer.println("Random string generated: " + new String(randomRow) + "\n" + "Number of Hashtags: " + numOfHash + "\n");

            System.out.println("Now processing UnHide with: " + numOfHash + " hashtags");
            //Start time
            long startTime = System.nanoTime();
            UnHide(randomRow, randomRow.length-1, writer);
            //Stop time
            long endTime = System.nanoTime();
            //Time difference
            long timeDiff = endTime - startTime;

            writer.println("\n"+"Time Taken (in Nanoseconds): " + timeDiff);
            writer.println("Time Taken (in Seconds): " + (double)timeDiff/1000000000 + "\n");

        }
        writer.close();
        System.out.println("ALL DONE!");




    }

}