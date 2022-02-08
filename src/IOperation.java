import java.util.Arrays;
import java.util.Random;

public class IOperation {
    private long startTime;
    private Puzzle[] population;
    private int generation;
    public IOperation(int size, long time){
        startTime = System.currentTimeMillis();
        population = new Puzzle[size];
        generation = 0;

        while(getTime()<time) nextGeneration();
    }

    /**
     * Creates the next generation of puzzles
     */
    public void nextGeneration(){
        Puzzle[] next = population.clone();
        culling(next);
        Puzzle parent1 = chooseParent(next);
        Puzzle parent2 = chooseParent(next);
        mutation(next, parent1, parent2);

        population = next;
        generation++;
    }

    /**
     * Replaces previous generation of puzzles with new ones, except for the elites
     * @param next Array of puzzles
     * @param puzzle1 parent 1
     * @param puzzle2 parent 2
     */
    public void mutation(Puzzle[] next, Puzzle puzzle1, Puzzle puzzle2){
    }

    public long getTime(){
        return (System.currentTimeMillis()-startTime)/1000;
    }

    /**
     * Chooses parents to make the next generation
     * @param next array of puzzles
     * @return one of the puzzles
     */
    private Puzzle chooseParent(Puzzle[] next){
        /* Check Assignment 2 video on the spreadsheet for implementation method*/
        double random = new Random().nextDouble();
        double cumulative = 0;
        double total = 0;
        for(Puzzle p: next){
            if(p!=null)
                total+=p.getScore();
        }
        for(Puzzle p: next){
            if(p!=null) {
                cumulative += p.getScore() / total;
                if (random < cumulative)
                    return p;
            }
        }
        return null;
    }

    /**
     * Culls members of puzzles
     * @param next Array of puzzles with the first 30% turned to null
     */
    private void culling(Puzzle[] next){
        Arrays.sort(next);
        for(int i = 0; i<next.length*3/10; i++){
            next[i]=null;
        }
    }

    public Puzzle[] getPopulation() {
        return population;
    }

    public void setPopulation(Puzzle[] population) {
        this.population = population;
    }
}
