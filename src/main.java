import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    /**
     * This is the main for the project, run this to have the A* algorithm run
     * @param args The first command line arg should be a heuristic number (1-6) and the second should be a filename (Without spaces)
     */
    public static void main(String[] args) {
        int currPuzzle;

        //checks if the input is correct and specifies what needs to be changed
        if(args.length != 3){
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
            System.out.println("Please input the puzzle number as the first input.");
            return;
        }
        if(!(currPuzzle == 1) && !(currPuzzle == 2)){
            System.out.println("Please input a correct puzzle number, either 1 or 2");
        }

        GenerateBoards currBoard;
        String puzzleFile = args[1];
        try {
             currBoard = new GenerateBoards(currPuzzle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(currPuzzle){
            case 1:
                puzzleFile = "src/boards/number_allocation.txt"; //TODO for quick testing but remove before submission
                ParseInput parseOne = new ParseInput(currPuzzle, puzzleFile);
                System.out.println(parseOne.getArrayList());
                break;
            case 2:
                puzzleFile = "src/boards/tower_building.txt"; //TODO for quick testing but remove before submission
                ParseInput parseTwo = new ParseInput(currPuzzle, puzzleFile);
                System.out.println(parseTwo.getArrayList());
                break;
        }

        long startTime = System.nanoTime();
        //actual alg goes here
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1000000000;
        System.out.println(elapsedTime);
    }
}