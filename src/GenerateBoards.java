import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateBoards {
    private final Random r;

    public GenerateBoards(int puzzle) throws IOException {
        r = new Random();
        choosePuzzle(puzzle);
    }

    public GenerateBoards(int puzzle, int seed) throws IOException {
        r = new Random(seed);
        choosePuzzle(puzzle);
    }

    private void choosePuzzle(int puzzle) throws IOException {
        File boards = new File("src/boards");
        boards.mkdir();
        switch (puzzle) {
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

    private void numberAllocation() throws IOException {
        FileWriter board = new FileWriter("src/boards/number_allocation.txt"); //Starts the file writer for a new text file
        float random;
        for (int i = 0; i < 40; i++) {
            random = -10 + r.nextFloat() * 20; //Random float with range [-10, 10]
            board.write(Float.toString(random));
            if (i != 39) board.write("\n");
        }
        board.close();
    }

    private void towerBuilding() throws IOException {
        FileWriter board = new FileWriter("src/boards/tower_building.txt"); //Starts the file writer for a new text file
        int random;
        for (int i = 0; i < 10; i++) { //TODO need to check later on how many pieces to build
            random = r.nextInt(2);
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
            for (int j = 0; j < 3; j++) {
                random = r.nextInt(5) + 1; //TODO clarification on "natural" range
                board.write(random + "\t");
            }
            if (i != 9) board.write("\n");
        }
        board.close();
    }
}
