import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseInput {

    public ParseInput(int puzzleNum, String puzzleFile, ArrayList arrayList) {
        switch(puzzleNum){
            case 1:
                arrayList = ParsePuzzleOne(puzzleFile);
                break;
            case 2:
                arrayList = ParsePuzzleTwo(puzzleFile);
                break;
        }
    }

    public ArrayList<Integer> ParsePuzzleOne(String puzzleFile){
        ArrayList<Integer> intList = new ArrayList<Integer>();
        try {
            File myObj = new File(puzzleFile);
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                try{
                    int currValue = Integer.parseInt(data);
                    intList.add(currValue);
                }
                catch (NumberFormatException e){
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

    public ArrayList<Piece> ParsePuzzleTwo(String puzzleFile){
        ArrayList<Piece> pieceList = new ArrayList<Piece>();
        try {
            File myObj = new File(puzzleFile);
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] spaces = data.split("\t");
                int width = Integer.parseInt(spaces[1]);
                int strength = Integer.parseInt(spaces[2]);
                int cost = Integer.parseInt(spaces[3]);
                if(spaces[0] == "Door"){
                    pieceList.add(new Piece(1,width, strength, cost));
                }
                if(spaces[0] == "Wall"){
                    pieceList.add(new Piece(2,width, strength, cost));
                }
                if(spaces[0] == "Lookout"){
                    pieceList.add(new Piece(3,width, strength, cost));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Input Read!");
        return pieceList;
    }
}
