import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseInput {
    private final ArrayList arrayList;

    public ParseInput(int puzzleNum, String puzzleFile) {
        switch (puzzleNum) {
            case 1:
                arrayList = ParsePuzzleOne(puzzleFile);
                break;
            case 2:
                arrayList = ParsePuzzleTwo(puzzleFile);
                break;
            default:
                arrayList = new ArrayList();
                break;
        }
    }

    private ArrayList<Float> ParsePuzzleOne(String puzzleFile) {
        ArrayList<Float> intList = new ArrayList<>();
        try {
            File myObj = new File(puzzleFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                try {
                    float currValue = Float.parseFloat(data);
                    intList.add(currValue);
                } catch (NumberFormatException e) {
                    System.out.println("Parse Input Error.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Input Read!");
        return intList;
    }

    private ArrayList<Piece> ParsePuzzleTwo(String puzzleFile) {
        ArrayList<Piece> pieceList = new ArrayList<>();
        try {
            File myObj = new File(puzzleFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] spaces = data.split("\t");
                int width = Integer.parseInt(spaces[1]);
                int strength = Integer.parseInt(spaces[2]);
                int cost = Integer.parseInt(spaces[3]);
                pieceList.add(new Piece(spaces[0], width, strength, cost));

            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Input Read!");
        return pieceList;
    }

    public ArrayList getArrayList() {
        return arrayList;
    }
}
