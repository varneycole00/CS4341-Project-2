import java.util.ArrayList;
import java.util.Random;

public class NumberAllocationOperation extends DefaultOperation {
    public NumberAllocationOperation(ArrayList<Float> nums, int population, int time) {
        super(population, time);
        NumberAllocation[] puzzles = new NumberAllocation[population];

        // create the first generation
        generateFirstGeneration(nums, puzzles);

        super.setPopulation(puzzles);

        while (getTime() < time) nextGeneration();

    }


    public void generateFirstGeneration(ArrayList<Float> startingNums, NumberAllocation[] puzzles) {
        for (int i = 0; i < puzzles.length; i++) {
            NumberAllocation currPuzzle = new NumberAllocation(startingNums);
            ArrayList<Float> copyOfFirstGen = (ArrayList<Float>) startingNums.clone();
            for (float j : copyOfFirstGen) {
                boolean placedInBin = false;
                while (!placedInBin) {
                    placedInBin = putInBin(j, currPuzzle, copyOfFirstGen);
                }
            }
            System.out.println("Puzzle made!");
            puzzles[i] = currPuzzle;
        }
    }

    public boolean putInBin(float currFloat, NumberAllocation currPuzzle, ArrayList<Float> copyOfFirstGen) {
        boolean returnBool = false;
        int rand = (int) (Math.floor(Math.random() * 4)) + 1;
        switch (rand) {
            case 1:
                if (currPuzzle.getBin1().size() < 10) {
                    currPuzzle.getBin1().add(currFloat);
                    returnBool = true;
                }
                break;
            case 2:
                if (currPuzzle.getBin2().size() < 10) {
                    currPuzzle.getBin2().add(currFloat);
                    returnBool = true;
                }
                break;
            case 3:
                if (currPuzzle.getBin3().size() < 10) {
                    currPuzzle.getBin3().add(currFloat);
                    returnBool = true;
                }
                break;
            case 4:
                if (currPuzzle.getBin4().size() < 10) {
                    currPuzzle.getBin4().add(currFloat);
                    returnBool = true;
                }
                break;
        }
        return returnBool;
    }

    @Override //TODO implement swapping and merging values of parents
    public Puzzle mutation(Puzzle puzzle1, Puzzle puzzle2) {
        NumberAllocation nA1 = (NumberAllocation) puzzle1;
        NumberAllocation nA2 = (NumberAllocation) puzzle2;
        return nA1;
    }
}
