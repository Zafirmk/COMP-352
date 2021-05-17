import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Driver class to test all 4 priority queue implementations
 */
@SuppressWarnings("all")
public class Driver {

    //String builder outputted to txt file
    static StringBuilder toPrint = new StringBuilder("Author: Zafir Khalid (40152164) & Marwa Khalid (40155098)" + "\n" + "\n");

    //Method that measures performance each type of PQ implementation
    static void measureTime(int nValue, MyPQSortedArray<Integer, String> pqSorted, MyPQUnsortedArray<Integer, String> pqUnSorted, MyPQSortedList<Integer, String> pqSortedList, MyPQUnsortedList<Integer, String> pqUnsortedList){
        
        //Declare variables
        System.out.println("\n");
        Scanner file1_sortedArr = null;
        Scanner file2_sortedArr = null;
        Scanner file3_sortedArr = null;
        Scanner file1_unSortedArr = null;
        Scanner file2_unSortedArr = null;
        Scanner file3_unSortedArr = null;
        Scanner file1_sortedLL = null;
        Scanner file2_sortedLL = null;
        Scanner file3_sortedLL = null;
        Scanner file1_unSortedLL = null;
        Scanner file2_unSortedLL = null;
        Scanner file3_unSortedLL = null;
        long start = 0, end = 0,
        sortedArrayInsert, sortedArrayRemove, 
        unsortedArrayInsert, unsortedArrayRemove,
        sortedListInsert, sortedListRemove,
        unsortedListInsert, unsortedListRemove;

        boolean file1used = false;
        boolean file2used = false;
        boolean file3used = false;
        String currString = null;

        //Check N value and appoint scanners appropriately
        if(nValue <= 10000){
            try {
                file1_sortedArr = new Scanner(new File("elements_test_file1.txt"));
                file1_unSortedArr = new Scanner(new File("elements_test_file1.txt"));
                file1_sortedLL = new Scanner(new File("elements_test_file1.txt"));
                file1_unSortedLL = new Scanner(new File("elements_test_file1.txt"));
                file1used = true;
                System.out.println("elements_test_file1.txt");
            } catch (FileNotFoundException e) {
                System.out.println("Error");
            }
        }
        else if(nValue > 10000 && nValue <= 100000){
            try {
                file2_sortedArr = new Scanner(new File("elements_test_file2.txt"));
                file2_unSortedArr = new Scanner(new File("elements_test_file2.txt"));
                file2_sortedLL = new Scanner(new File("elements_test_file2.txt"));
                file2_unSortedLL = new Scanner(new File("elements_test_file2.txt"));
                file2used = true;
                System.out.println("elements_test_file2.txt");
            } catch (FileNotFoundException e) {
                System.out.println("Error");
            } 
        }
        else{
            try {
                file3_sortedArr = new Scanner(new File("elements_test_file3.txt"));
                file3_unSortedArr = new Scanner(new File("elements_test_file3.txt"));
                file3_sortedLL = new Scanner(new File("elements_test_file3.txt"));
                file3_unSortedLL = new Scanner(new File("elements_test_file3.txt"));
                file3used = true;
                System.out.println("elements_test_file3.txt");
            } catch (FileNotFoundException e) {
                System.out.println("Error");
            }  
        }


        //Inserting n elements into sorted PQ Array
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < nValue; i++){
            if(file1used){
                currString = file1_sortedArr.nextLine();
            }
            else if(file2used){
                currString = file2_sortedArr.nextLine();
            }
            else if(file3used){
                currString = file3_sortedArr.nextLine();
            }
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            pqSorted.insert(randomNum, currString);
        }
        long endTime = System.currentTimeMillis();
        sortedArrayInsert = endTime - startTime;
        System.out.println("Time taken to insert " + nValue + " items into sorted PQ : " + sortedArrayInsert + "ms");
        
        startTime = 0;
        endTime = 0;

        //Deleting n elements from sorted PQ Array
        startTime = System.currentTimeMillis();
        for(int i = 0; i < nValue; i++){
            pqSorted.removeMin();
        }
        endTime = System.currentTimeMillis();
        sortedArrayRemove = endTime - startTime;
        System.out.println("Time taken to delete " + nValue + " items from sorted PQ : " + sortedArrayRemove + "ms");

        startTime = 0;
        endTime = 0;

        //Inserting n elements into unsorted PQ Array
        startTime = System.currentTimeMillis();
        for(int i = 0; i < nValue; i++){
            if(file1used){
                currString = file1_unSortedArr.nextLine();
            }
            else if(file2used){
                currString = file2_unSortedArr.nextLine();
            }
            else if(file3used){
                currString = file3_unSortedArr.nextLine();
            }
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            pqUnSorted.insert(randomNum, currString);
        }
        endTime = System.currentTimeMillis();
        unsortedArrayInsert = endTime - startTime;
        System.out.println("Time taken to insert " + nValue + " items into unsorted PQ : " + unsortedArrayInsert + "ms");

        //Deleting n elements from unsorted PQ Array
        startTime = System.currentTimeMillis();
        for(int i = 0; i < nValue; i++){
            pqUnSorted.removeMin();
        }
        endTime = System.currentTimeMillis();
        unsortedArrayRemove = endTime - startTime;
        System.out.println("Time taken to delete " + nValue + " items from unsorted PQ : " + unsortedArrayRemove + "ms");


        startTime = 0;
        endTime = 0;

        //Inserting n elements into unsorted PQ Linked list
        startTime = System.currentTimeMillis();
        for(int i = 0; i < nValue; i++){
            if(file1used){
                currString = file1_unSortedLL.nextLine();
            }
            else if(file2used){
                currString = file2_unSortedLL.nextLine();
            }
            else if(file3used){
                currString = file3_unSortedLL.nextLine();
            }
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            pqUnsortedList.insert(randomNum, currString);
        }
        endTime = System.currentTimeMillis();
        unsortedListInsert = endTime - startTime;
        System.out.println("Time taken to insert " + nValue + " items into unsorted Linked list PQ : " + unsortedListInsert + "ms");

        //Deleting n elements from unsorted PQ Linked list
        startTime = System.currentTimeMillis();
        for(int i = 0; i < nValue; i++){
            pqUnsortedList.removeMin();
        }
        endTime = System.currentTimeMillis();
        unsortedListRemove = endTime - startTime;
        System.out.println("Time taken to delete " + nValue + " items from unsorted Linked list PQ : " + unsortedListRemove + "ms");

        startTime = 0;
        endTime = 0;

        //Inserting n elements into sorted PQ Linked list
        startTime = System.currentTimeMillis();
        for(int i = 0; i < nValue; i++){
            if(file1used){
                currString = file1_sortedLL.nextLine();
            }
            else if(file2used){
                currString = file2_sortedLL.nextLine();
            }
            else if(file3used){
                currString = file3_sortedLL.nextLine();
            }
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            pqSortedList.insert(randomNum, currString);
        }
        endTime = System.currentTimeMillis();
        sortedListInsert = endTime - startTime;
        System.out.println("Time taken to insert " + nValue + " items into a sorted Linked list PQ : " + sortedListInsert + "ms");

        //Deleting n elements from sorted PQ Linked list
        startTime = System.currentTimeMillis();
        for(int i = 0; i < nValue; i++){
            pqSortedList.removeMin();
        }
        endTime = System.currentTimeMillis();
        sortedListRemove = endTime - startTime;
        System.out.println("Time taken to delete " + nValue + " items from a sorted Linked list PQ : " + sortedListRemove + "ms");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");

        //Formatting and building string
        toPrint.append("---------------------------------------------------------------\n");
        toPrint.append(String.format("|%20s|%20s|%20s|\n", "N = " + nValue, "Insert(k,v) ms", "RemoveMin() ms"));
        toPrint.append("---------------------------------------------------------------\n");
        toPrint.append(String.format("|%20s|%20s|%20s|\n", "UnsortedArray", unsortedArrayInsert + " ms", unsortedArrayRemove + " ms"));
        toPrint.append("---------------------------------------------------------------\n");
        toPrint.append(String.format("|%20s|%20s|%20s|\n", "SortedArray", sortedArrayInsert + " ms", sortedArrayRemove + " ms"));
        toPrint.append("---------------------------------------------------------------\n");
        toPrint.append(String.format("|%20s|%20s|%20s|\n", "SortedList", sortedListInsert + " ms", sortedListRemove + " ms"));
        toPrint.append("---------------------------------------------------------------\n");
        toPrint.append(String.format("|%20s|%20s|%20s|\n", "UnsortedList", unsortedListInsert + " ms", unsortedListRemove + " ms"));
        toPrint.append("---------------------------------------------------------------\n");
        toPrint.append("\n\n\n\n");

    }




    public static void main(String[] args) {
        
        int currN = 0;
        int[] nValues = {10, 100, 1000, 10000, 100000};

        for (int i=0; i<nValues.length; i++){
            measureTime(nValues[i], new MyPQSortedArray<Integer, String>(), new MyPQUnsortedArray<Integer, String>(), new MyPQSortedList<Integer, String>(), new MyPQUnsortedList<Integer, String>());
        }

        PrintWriter testPQwriter = null;
        try {
            testPQwriter = new PrintWriter(new File("pqtestrun.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error while writing to file");
        }

        testPQwriter.println(toPrint);
        testPQwriter.close();


    }
}
