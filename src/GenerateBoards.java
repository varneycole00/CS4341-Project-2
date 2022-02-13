import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The class for creating the boards
 */
public class GenerateBoards {
    private final Random r; // creating a random variable to create the boards with

    /**
     * Constructor for the class
     * @param puzzle takes in a puzzle number and creates a board depending on the input
     * @throws IOException catches a exception if IO fails
     */
    public GenerateBoards(int puzzle) throws IOException {
        r = new Random(); // initializes the random variable
        choosePuzzle(puzzle); // calls helper function to run the specific input creator
    }

    /**
     * Constructor for testing the class
     * @param puzzle takes in a puzzle number and creates a board depending on if the input is 1 or 2
     * @param seed takes in an int seed for testing to allow for identical input for the algorithms
     * @throws IOException catches an exception if IO fails
     */
    public GenerateBoards(int puzzle, int seed) throws IOException {
        r = new Random(seed); // initializes the random variable depending on an inputted seed (for testing)
        choosePuzzle(puzzle); // calls helper function to run the specific input generator
    }

    /**
     * Helper function for the class constructor
     * Depending on the input, it will begin the text file generation
     * @param puzzle int representing if we are solving puzzle 1 or 2
     * @throws IOException catches an exception if IO fails
     */
    private void choosePuzzle(int puzzle) throws IOException {
        File boards = new File("src/boards"); // generating a new file
        boards.mkdir();
        switch (puzzle) {  // depending on which puzzle we solve, we need to create different input files
            case 1:
                numberAllocation();
                break;
            case 2:
                towerBuilding();
                break;
            default:
                break;
        }
    }

    /**
     * Creates a file with 40 floats with a newline between them
     * @throws IOException catches IO errors
     */
    private void numberAllocation() throws IOException {
        FileWriter board = new FileWriter("src/boards/number_allocation.txt"); //Starts the file writer for a new text file
        float random;
        ArrayList<Float> boardNums = new ArrayList<>(); // arraylist to store all the numbers we are generating as to avoid duplicates
        for (int i = 0; i < 40; i++) {
            random = -10 + r.nextFloat() * 20; // Random float with range [-10, 10]
            if (boardNums.contains(random)) { // if already existing within the input, we try again
                i--;
            } else {  // if it does not exist in the input, we add it to the arraylist
                boardNums.add(random);
            }
        } // take all the numbers that are in the arraylist and place them in the file with a newline separating them
        for (int j = 0; j < boardNums.size(); j++) {
            board.write(Float.toString(boardNums.get(j)));
            if (j != 39) board.write("\n");
        }
        board.close();
    }

    /**
     * method to create a file for puzzle 2
     * generates a piece name, width, strength, and cost all at random
     * @throws IOException catches IO exception if issues arise
     */
    private void towerBuilding() throws IOException {
        FileWriter board = new FileWriter("src/boards/tower_building.txt"); //Starts the file writer for a new text file
        int random;  //
        for (int i = 0; i < 10; i++) {  // runs loop 10x to create 10 pieces
            random = r.nextInt(3);  // randomly chooses the type of tower piece
            switch (random) {
                case 0:
                    board.write("Door\t");
                    break;
                case 1:
                    board.write("Wall\t");
                    break;
                case 2:
                    board.write("Lookout\t");
                    break;
                default:
                    break;
            }
            for (int j = 0; j < 3; j++) {  // generates the strength, width, and cost randomly
                random = r.nextInt(9) + 1;
                board.write(random + "\t");
            }
            if (i != 9) board.write("\n"); // separates the pieces with a newline
        }
        board.close();   // close the file
    }
}
