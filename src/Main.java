import java.io.IOException;
import java.util.ArrayList;

public class Main {
    /**
     * This is the main for the project, run this to have the GA algorithm run and solve a puzzle
     *
     * @param args The first command line arg should be which puzzle type (1 or 2), the second should be the file path,
     *             and the third should be the amount of seconds to have the algorithm run before giving an answer
     */
    public static void main(String[] args) {
        int currPuzzle;

        // checks if the input is correct and specifies what needs to be changed
        if (args.length != 3) {
            System.out.println("An error occurred.");
            System.out.println("Invalid number of input. ");
            System.out.println("Please input a puzzle number, filename, and a time-limit to solve the puzzle ");
        }

        // reads the first command line arg as the puzzle number as a string
        String initialInt = args[0];

        try { // attempts to convert it to integer, otherwise catch the error and exit
            currPuzzle = Integer.parseInt(initialInt);
        } catch (NumberFormatException e) {
            System.out.println("Please input the puzzle number as the first input.");
            return;
        }

        // generates the one of each type of board, using random generated numbers

        String puzzleFile = args[1]; // gets the filepath for the puzzle we will solve from command line
        String timeString = args[2];
        int time;
        try { // gets the runtime from the commandline and attempts to convert it from a string to int, if unsuccessful, return
            time = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Please input the runtime as the third input.");
            return;
        }

        try {   //generates the input depending on which puzzle we would like to solve (1 or 2)
            GenerateBoards currBoard = new GenerateBoards(currPuzzle);
        } catch (IOException e) { // catches exception if not completed
            e.printStackTrace();
        }


        switch (currPuzzle) {  // depending on which puzzle we will solve, we call a different algorithm under the same interface
            case 1:  // if first puzzle, we denote where the input will come from
                ParseInput parseOne = new ParseInput(currPuzzle, puzzleFile); // we parse the input and place it into respective classes and objects
                new NumberAllocationOperation((ArrayList<Float>) parseOne.getArrayList(), 10, time); // we run the algorithm depending on population size and time
                break;
            case 2:  // if second puzzle, we denote where the input will come from
                ParseInput parseTwo = new ParseInput(currPuzzle, puzzleFile); // we parse through the input
                new TowerBuildingOperation((ArrayList<Piece>) parseTwo.getArrayList(), 100, time); // we run the algorithm for the population size and time
                break;
            default: // if the puzzle input is wrong, we will catch it here as well
                System.out.println("Please input a correct puzzle number, either 1 or 2");
                break;
        }
    }
}