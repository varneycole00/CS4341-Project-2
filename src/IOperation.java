import java.util.Arrays;
import java.util.Random;

public class IOperation {
    private final long startTime;
    private Puzzle[] population;
    private int generation;

    public IOperation(int size, long time) {
        startTime = System.currentTimeMillis();
        population = new Puzzle[size];
        generation = 0;
    }

    /**
     * Creates the next generation of puzzles
     */
    public void nextGeneration() {
        Puzzle parent1, parent2;
        Puzzle[] next = new Puzzle[population.length];
        Arrays.sort(population);
        culling(population);

        for (int i = 0; i < next.length * 2 / 10; i++) { //Gets the best from previous generation. Elitism
            next[i] = population[population.length - i - 1];
        }

        for (int i = next.length * 2 / 10; i < next.length; i += 2) {
            parent1 = chooseParent(population);
            do {
                parent2 = chooseParent(population);
            } while (parent1 == parent2);

            next[i] = mutation(parent1, parent2);
            if (i + 1 < next.length)
                next[i + 1] = mutation(parent2, parent1);

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
    public Puzzle mutation(Puzzle puzzle1, Puzzle puzzle2) {
        return null;
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
        for (Puzzle p : next) {
            if (p != null)
                total += p.getScore();
        }
        for (Puzzle p : next) {
            if (p != null) {
                cumulative += p.getScore() / total;
                if (random < cumulative)
                    return p;
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
