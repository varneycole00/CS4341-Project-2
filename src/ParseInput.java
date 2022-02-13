import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Converts .txt file in correct format into data for the corresponding puzzle
 */
public class ParseInput {
    private final ArrayList arrayList; //Data for puzzle

    /**
     * Converts the String from the .txt file into the data needed for the puzzle
     * @param puzzleNum Puzzle type (1-2)
     * @param puzzleFile Text for the data
     */
    public ParseInput(int puzzleNum, String puzzleFile) {
        switch (puzzleNum) {
            case 1: //Puzzle 1
                arrayList = ParsePuzzleOne(puzzleFile);
                break;
            case 2: //Puzzle 2
                arrayList = ParsePuzzleTwo(puzzleFile);
                break;
            default:
                arrayList = new ArrayList();
                break;
        }
    }

    /**
     * Converts string into format for puzzle 1
     * @param puzzleFile String of data
     * @return ArrayList of floats from the data
     */
    private ArrayList<Float> ParsePuzzleOne(String puzzleFile) {
        ArrayList<Float> intList = new ArrayList<>();
        try { //Reads the Floats from the file
            File myObj = new File(puzzleFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                try {
                    float currValue = Float.parseFloat(data); //Converts string into float
                    intList.add(currValue); //Adds float to data
                } catch (NumberFormatException e) {
                    System.out.println("Parse Input Error.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Input Read!");
        return intList; //Return all the data
    }

    /**
     * Converts string into format for puzzle 2
     * @param puzzleFile String of data
     * @return ArrayList of Pieces from the data
     */
    private ArrayList<Piece> ParsePuzzleTwo(String puzzleFile) {
        ArrayList<Piece> pieceList = new ArrayList<>();
        try {
            File myObj = new File(puzzleFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] spaces = data.split("\t"); // Gets the properties of the tower
                int width = Integer.parseInt(spaces[1]); // Width of Tower
                int strength = Integer.parseInt(spaces[2]); // Strength
                int cost = Integer.parseInt(spaces[3]); // Cost
                pieceList.add(new Piece(spaces[0], width, strength, cost)); //Adds new piece with the corresponding values into the ArrayList

            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Input Read!");
        return pieceList; //Returns the ArrayList of pieces
    }

    /**
     * Get the data from .txt file
     * @return ArrayList of Floats for puzzle 1 or Pieces for Puzzle 2
     */
    public ArrayList getArrayList() {
        return arrayList;
    }
}
