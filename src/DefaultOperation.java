import java.util.Arrays;
import java.util.Random;

public class DefaultOperation {
    private final long startTime;
    private Puzzle[] population;
    private int generation;

    public DefaultOperation(int size) {
        startTime = System.currentTimeMillis();
        population = new Puzzle[size];
        generation = 0;
    }

    /**
     * Creates the next generation of puzzles
     */
    public void nextGeneration() {
        int PERCENTAGE = 20;
        System.out.println(generation);
        Puzzle parent1, parent2;
        Puzzle[] next = new Puzzle[population.length];
        Arrays.sort(population);
        culling(population);

        for (int i = 0; i < next.length * PERCENTAGE / 100; i++) { //Gets the best from previous generation. Elitism
            next[i] = population[population.length - i - 1];
        }

        for (int i = next.length * PERCENTAGE / 100; i < next.length; i += 2) {
            Puzzle[] parents = population.clone();
            parent1 = chooseParent(parents);
            parent2 = chooseParent(parents);
            System.out.println("Parents Chosen");
            Puzzle[] children = mutation(parent1, parent2);
            System.out.println("Mutation Complete");
            next[i] = children[0];
            if (i + 1 < next.length)
                next[i + 1] = children[1];
        }
        population = next;
        generation++;
    }

    /**
     * Replaces previous generation of puzzles with new ones, except for the elites
     *
     * @param puzzle1 parent 1
     * @param puzzle2 parent 2
     */
    public Puzzle[] mutation(Puzzle puzzle1, Puzzle puzzle2) {
        Puzzle[] mutationReturn = {puzzle2, puzzle2};
        return mutationReturn;
    }

    public long getTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    /**
     * Chooses parents to make the next generation
     *
     * @param next array of puzzles
     * @return one of the puzzles
     */
    private Puzzle chooseParent(Puzzle[] next) { //TODO duplication prevention
        /* Check Assignment 2 video on the spreadsheet for implementation method*/
        double random = new Random().nextDouble();
        double cumulative = 0;
        double total = 0;
        double lowestScore = Double.MAX_VALUE;
        for (Puzzle p : next) {
            if (p != null) {
                if (lowestScore == Double.MAX_VALUE)
                    lowestScore = p.getScore()+1;
                total += p.getScore() + lowestScore;
            }
        }
        for (Puzzle p : next) {
            if (p != null) {
                cumulative += (p.getScore() + lowestScore) / total;
                if (random < cumulative) {
                    Puzzle parent = p;
                    p = null;
                    return parent;
                }

            }
        }
        return null;
    }

    /**
     * Culls members of puzzles
     *
     * @param next Array of puzzles with the first 30% turned to null
     */
    private void culling(Puzzle[] next) {
        for (int i = 0; i < next.length * 3 / 10; i++) {
            next[i] = null;
        }
    }

    public Puzzle[] getPopulation() {
        return population;
    }

    public void setPopulation(Puzzle[] population) {
        this.population = population;
    }
}
