import java.util.Arrays;
import java.util.Random;

/**
 * The generalized Genetic Algorithm
 */
public class DefaultOperation {
    private final long startTime; //Time when algorithm is started. Used to calculate how many seconds have passed
    private Puzzle[] population; //Current population of Puzzles
    private int generationSame; //How many times the largest puzzle score stays the same. Used for random restarts
    private Puzzle largestParent; //Largest Puzzle from all iterations of random start
    private Puzzle currentLargest; //Largest Puzzle from current iteration of random start
    private int generation; //Gets total number of generations
    private int genFound; //Gets generation the largest was found

    /**
     * Creates an algorithm of Puzzles with a set population size
     * @param size Number of puzzles in each generation
     */
    public DefaultOperation(int size) {
        startTime = System.currentTimeMillis(); // Time when algorithm is started. The timer begins when the object is created
        population = new Puzzle[size];  // we generate the population based on the inputted initial population size (from main)
        generationSame = 0;  // counter to check if all objects in the current population are the same, if so we will do random restart
        genFound = 0;  // counter for when the most optimal answer currently found was.
        largestParent = null;  // Largest Puzzle from all iterations of random start
        currentLargest = null;  // Largest Puzzle from current iteration of random start
    }

    /**
     * Creates the next generation of puzzles
     */
    public void nextGeneration() {
        int PERCENTAGE = 20; //Percentage of current population to bring over
        Puzzle parent1, parent2; //Parents to make children for mutations
        Puzzle[] next = new Puzzle[population.length]; //Next generation
        Arrays.sort(population); //Sorts current generation from least to greatest score

        /*
            Compares best Puzzle in this iteration with the best Puzzle in this generation
            If they're the same genLargest is updated to indicate we might be at a plateau.
            If this gen's largest is larger currentLargest is updated and genLargest is reset.
         */
        Puzzle genLargest = population[population.length - 1]; //Generational largest
        if (currentLargest == null)  // if no current largest exists, then we set it to the largest in the current generation
            currentLargest = genLargest;
        else if (genLargest.getScore() == currentLargest.getScore())  // if the current largest is equal to the overall largest, then we increment the counter for the highest score staying the same
            generationSame++;
        else if (genLargest.getScore() > currentLargest.getScore()) {  // if the current largest is the greater than before, we replace it
            currentLargest = genLargest;
            generationSame = 0;
        }

        /*
            Compares best Puzzle from all iterations with the current iteration. If current largest
            is better it replaces the old best and updates genFound.
         */
        if (largestParent == null) {
            largestParent = currentLargest;
            genFound = generation;
        }
        else if(largestParent.getScore() < currentLargest.getScore()) {
            largestParent = currentLargest;
            genFound = generation;
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
        if (generation % 10000 == 0)
            System.out.println("Generation " + generation + " reached"); //Updates the user so they know the algorithm isn't stuck and is still going
    }

    /**
     * Replaces previous generation of puzzles with new ones, except for the elites
     * @param puzzle1 parent 1
     * @param puzzle2 parent 2
     */
    public Puzzle[] mutation(Puzzle puzzle1, Puzzle puzzle2) {
        Puzzle[] mutationReturn = {puzzle2, puzzle2};
        return mutationReturn;
    }

    /**
     * Gets time elapsed
     * @return time in seconds from start of algorithm
     */
    public long getTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    /**
     * Chooses parents to make the next generation
     * @param next array of puzzles
     * @return one of the puzzles
     */
    private Puzzle chooseParent(Puzzle[] next) {
        double random = new Random().nextDouble(); //Random double from 0 to 1
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
        double average = total / count; //Gets average score
        if(total == 0){ //Picks random parent if the total score is 0, which only happens if the values of all potential parents are 0
            Puzzle parent = null;
            int i = 0;
            while (parent == null) {
                i = new Random().nextInt(next.length);
                parent = next[i]; //Gets parent to return
            }
            next[i] = null; //Sets parent to null so it can't be chosen again by parent2. This is reverted after the for loop in nextGeneration()
            return parent;
        } else
            for (int i = 0; i < next.length; i++) { //Picks parent at random
                if (next[i] != null) {
                    cumulative += (next[i].getScore() + lowestScore + average) / (total+ average*count); //Calculates cumulative percentage. Lowest Score and Average is added to weigh negative numbers properly
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
     * @param next Array of puzzles with the first 30% turned to null
     */
    private void culling(Puzzle[] next) {
        int PERCENTAGE = 30; //Percentage of population to cull
        for (int i = 0; i < next.length * PERCENTAGE / 100; i++) {
            next[i] = null;
        }
    }

    /**
     * Starts the algorithm again with a new set of puzzles
     * @param newPuzzle New set of puzzles to perform the algorithm on
     */
    public void randomRestart(Puzzle[] newPuzzle) {
        generationSame = 0;
        population = newPuzzle.clone();
        currentLargest = null;
    }

    // getters and setters for the class

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