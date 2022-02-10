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
        NumberAllocation Child1 = new NumberAllocation(((NumberAllocation) puzzle1).getAllNum());
        NumberAllocation Child2 = new NumberAllocation(((NumberAllocation) puzzle2).getAllNum());

        // bin1 swap
        ArrayList<Float> Child1Bin1 = new ArrayList<>();
        ArrayList<Float> Child2Bin1 = new ArrayList<>();
        ArrayList<Float> Child1Bin2 = new ArrayList<>();
        ArrayList<Float> Child2Bin2 = new ArrayList<>();
        ArrayList<Float> Child1Bin3 = new ArrayList<>();
        ArrayList<Float> Child2Bin3 = new ArrayList<>();
        ArrayList<Float> Child1Bin4 = new ArrayList<>();
        ArrayList<Float> Child2Bin4 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                Child1Bin1.add(nA1.getBin1().get(i));
                Child2Bin1.add(nA2.getBin1().get(i));

                Child1Bin2.add(nA1.getBin2().get(i));
                Child2Bin2.add(nA2.getBin2().get(i));

                Child1Bin3.add(nA1.getBin3().get(i));
                Child2Bin3.add(nA2.getBin3().get(i));

                Child1Bin4.add(nA1.getBin4().get(i));
                Child2Bin4.add(nA2.getBin4().get(i));
            } else {
                Child1Bin1.add(nA2.getBin1().get(i));
                Child2Bin1.add(nA1.getBin1().get(i));

                Child1Bin2.add(nA2.getBin2().get(i));
                Child2Bin2.add(nA1.getBin2().get(i));

                Child1Bin3.add(nA2.getBin3().get(i));
                Child2Bin3.add(nA1.getBin3().get(i));

                Child1Bin4.add(nA2.getBin4().get(i));
                Child2Bin4.add(nA1.getBin4().get(i));
            }
        }
        Child1.setBin1(Child1Bin1);
        Child2.setBin1(Child2Bin1);

        Child1.setBin2(Child1Bin2);
        Child2.setBin2(Child2Bin2);

        Child1.setBin3(Child1Bin3);
        Child2.setBin3(Child2Bin3);

        Child1.setBin4(Child1Bin4);
        Child2.setBin4(Child2Bin4);


        // what to return?
        return nA1;
    }
}
