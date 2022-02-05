import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    /**
     * This is the main for the project, run this to have the A* algorithm run
     * @param args The first command line arg should be a heuristic number (1-6) and the second should be a filename (Without spaces)
     */
    public static void main(String[] args) {
        int currPuzzle = 0;

        //checks if the input is correct and specifies what needs to be changed
        if(args.length < 3 || args.length > 3){
            System.out.println("An error occurred.");
            System.out.println("Invalid number of input. ");
            System.out.println("Please input a puzzle number, filename, and a time-limit to solve the puzzle ");
        }

        //reads the first command line arg as the puzzle number
        String initialInt =  args[0];
        try{
            currPuzzle = Integer.parseInt(initialInt);
        }
        catch (NumberFormatException e){
            System.out.println("Please input the filename as the first input.");
            return;
        }

//        //reads the second input as the filename and opens the file
//        String Filename = args[1];
//        try {
//            File myObj = new File("C:\\Users\\Josh\\IdeaProjects\\Intro to AI\\src\\" + Filename);
//            Scanner myReader = new Scanner(myObj);
//            int i = 0;
//            //while the file is open, we parse through it to calculate the board size
//            while (myReader.hasNextLine()) {
//                //parse values
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }



        long startTime = System.nanoTime();
        //actual alg goes here
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1000000000;
        System.out.println(elapsedTime);
    }
}