import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Stack;

public class Iterative {

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
            }
            randomString.setCharAt(index, '#');
        }

        //Convert string to array of chars and return
        char[] toReturn = new char[randomString.length()];
        randomString.getChars(0, randomString.length(), toReturn, 0);
        return (toReturn);

    }

    /**
     * UnHide iterative method to find all permutations of the row
     * @param s - Row containing X's O's and #'s
     * @param writer - Printwriter to output to the file
     */
    public static void UnHide(char[] s, PrintWriter writer) {
        String stringToProcess = new String(s);
        Stack<String> stack = new Stack<String>();
        stack.push(stringToProcess);

        //While processing stack isn't empty
        while (stack.empty() != true) {

            //Current String
            String currString = stack.pop();
            //Index of #
            int hashIndex = currString.indexOf('#');

            //If hash found
            if (hashIndex != -1) {
                //Replace hash with X and push then O and push
                for (int i = 1; i <= 2; i++) {
                    if (i == 1) {
                        currString = currString.substring(0, hashIndex) + "X" + currString.substring(hashIndex + 1);
                    } else {
                        currString = currString.substring(0, hashIndex) + "O" + currString.substring(hashIndex + 1);
                    }
                    stack.push(currString);
                }
            //If no #'s found print the string
            } else {
                writer.println(currString);
            }
        }
    }

    public static void main(String[] args) {

        PrintWriter writer = null;

        //Initialize print writer
        try {
            writer = new PrintWriter("out(Iterative).txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error Occured");
        }

        //Call the UnHide function certain number of times
        for(int i = 1; i<=12; i++){
            writer.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            //Number of hashes is always even
            int numOfHash = i*2;
            char[] randomRow = getRandomString(numOfHash);
            writer.println("Random string generated: " + new String(randomRow) + "\n" + "Number of Hashtags: " + numOfHash + "\n");
            writer.flush();

            System.out.println("Now processing UnHide with: " + numOfHash + " hashtags");
            //Start time
            long startTime = System.nanoTime();
            UnHide(randomRow, writer);
            //End time
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
