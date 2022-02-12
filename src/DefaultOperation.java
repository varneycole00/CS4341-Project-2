import java.util.Arrays;
import java.util.Random;

public class DefaultOperation {
    private final long startTime;
    private Puzzle[] population;
    private int generationSame;
    private Puzzle largestParent;
    private Puzzle currentLargest;
    private int generation;
    private int genFound;

    public DefaultOperation(int size) {
        startTime = System.currentTimeMillis();
        population = new Puzzle[size];
        generationSame = 0;
        genFound = 0;
        largestParent = null;
        currentLargest = null;
    }

    /**
     * Creates the next generation of puzzles
     */
    public void nextGeneration() {
        int PERCENTAGE = 20; //Percentage of current population to bring over
        Puzzle parent1, parent2; //Parents to make children for mutations
        Puzzle[] next = new Puzzle[population.length]; //Next generation
        Arrays.sort(population); //Sorts current generation from least to greatest score
        Puzzle genLargest = population[population.length-1];
        if(currentLargest == null)
            currentLargest = genLargest;
        else if(genLargest.getScore() == currentLargest.getScore())
            generationSame++;
        else if(genLargest.getScore() > currentLargest.getScore()) {
            currentLargest = genLargest;
            generationSame = 0;
        }

        culling(population); //Removes the lowest scoring members of the population

        for (int i = 0; i < next.length * PERCENTAGE / 100; i++) { //Gets the best from previous generation. Elitism
            next[i] = population[population.length - i - 1];
        }

        for (int i = next.length * PERCENTAGE / 100; i < next.length; i += 2) { //Adds children
            Puzzle[] parents = population.clone(); //Gets a clone of the current, culled population
            parent1 = chooseParent(parents); //Chooses parent1 by weighted random
            parent2 = chooseParent(parents); //Chooses parent2 by weighted random
           // System.out.println("Parents Chosen");
            Puzzle[] children = mutation(parent1, parent2); //Gets the mutated children of the parents
            //System.out.println("Mutation Complete");
            next[i] = children[0]; //Adds the first child
            if (i + 1 < next.length) //Adds the second child if there is still space in the next generation
                next[i + 1] = children[1];
        }

        population = next; //Changes the current population to be the updated generation
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
    private Puzzle chooseParent(Puzzle[] next) {
        double random = new Random().nextDouble(); //Random double from 0 to 1
                                                              // TODO remove seed from random
        double cumulative = 0; //Cumulative percentage
        double total = 0; //Total score of population
        double lowestScore = Double.MAX_VALUE; //Used for scores in the negatives to properly work with weighted percentage
        double count = 0;
        for (Puzzle p : next) { //Gets total score
            if (p != null) {
                count++;
                if (p.getScore() < lowestScore) {
                    lowestScore = p.getScore(); //Sets lowestScore as the parent with the lowest score
                }
                total += (p.getScore() + lowestScore); //Total is the sum of the actual parent score and the lowest score so all the numbers are positive
            }
        }
        double average = total / count;
        for (int i = 0; i < next.length; i++) { //Picks parent at random
            if (next[i] != null) {
                cumulative += (next[i].getScore() + lowestScore + average) / total; //Calculates cumulative percentage
                if (random < cumulative) { //If random is less than cumulative, then it is with in the range of the parent
                    Puzzle parent = next[i]; //Gets parent to return
                    next[i] = null; //Sets parent to null so it can't be chosen again by parent2. This is reverted after the for loop in nextGeneration()
                    return parent;
                }
            }
        }

        return null; //Default case
    }

    /**
     * Culls members of puzzles
     *
     * @param next Array of puzzles with the first 30% turned to null
     */
    private void culling(Puzzle[] next) {
        int PERCENTAGE = 30; //Percentage of population to cull
        for (int i = 0; i < next.length * PERCENTAGE / 100; i++) {
            next[i] = null;
        }
    }

    public void randomRestart(Puzzle[] newPuzzle){
        if(largestParent==null) {
            largestParent = currentLargest;
            genFound = generation;
        }
        else if(largestParent.getScore() < currentLargest.getScore()) {
            largestParent = currentLargest;
            genFound = generation;
        }

        generationSame = 0;
        population = newPuzzle.clone();
        currentLargest = null;
    }


    public Puzzle[] getPopulation() {
        return population;
    }

    public void setPopulation(Puzzle[] population) {
        this.population = population;
    }

    public int getGenerationSame() {
        return generationSame;
    }

    public void setGeneration(int generation) {
        this.generationSame = generation;
    }

    public Puzzle getLargestParent() {
        return largestParent;
    }

    public int getGeneration() {
        return generation;
    }

    public int getGenFound() {
        return genFound;
    }
}
