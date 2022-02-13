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

        // reads the first command line arg as the puzzle number
        String initialInt = args[0];

        try {
            currPuzzle = Integer.parseInt(initialInt);
        } catch (NumberFormatException e) {
            System.out.println("Please input the puzzle number as the first input.");
            return;
        }

        String puzzleFile = args[1];
        int time = Integer.parseInt(args[2]);

        try {
            GenerateBoards currBoard = new GenerateBoards(currPuzzle);
        } catch (IOException e) {
            e.printStackTrace();
        }


        switch (currPuzzle) {
            case 1:
                ParseInput parseOne = new ParseInput(currPuzzle, puzzleFile);
                new NumberAllocationOperation((ArrayList<Float>) parseOne.getArrayList(), 10, time);
                break;
            case 2:
                ParseInput parseTwo = new ParseInput(currPuzzle, puzzleFile);
                new TowerBuildingOperation((ArrayList<Piece>) parseTwo.getArrayList(), 100, time);
                break;
            default:
                System.out.println("Please input a correct puzzle number, either 1 or 2");
                break;
        }
    }
}