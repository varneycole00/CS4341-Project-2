import java.util.ArrayList;

public class NumberAllocationOperation extends IOperation{
    public NumberAllocationOperation(ArrayList<Float> nums, int population, int time){
        super(population, time);
        NumberAllocation[] puzzles = new NumberAllocation[population];
        super.setPopulation(puzzles);
    }

    @Override //TODO implement swapping and merging values of parents
    public void mutation(Puzzle[] next, Puzzle puzzle1, Puzzle puzzle2) {
        NumberAllocation nA1 = (NumberAllocation) puzzle1;
        NumberAllocation nA2 = (NumberAllocation) puzzle2;
    }
}
