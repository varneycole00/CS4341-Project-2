import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Class for the mutations and conflict resolution of the puzzle 1
 */
public class NumberAllocationOperation extends DefaultOperation {
    ArrayList<Float> numbers = new ArrayList<>(); // an arraylist of all the numbers inputted

    /**
     * constructor for this class
     * @param nums takes in the numbers inputted from the text file
     * @param population takes in the size of the population to generate
     * @param time the max time that the algorithm will run
     */
    public NumberAllocationOperation(ArrayList<Float> nums, int population, int time) {
        super(population); // set the population size in the superclass (default operation) -> we treated this like an interface
        int RESTART = 5; // Number of generations before random restart
        NumberAllocation[] puzzles = new NumberAllocation[population]; // creates an array to store all the puzzles in the current generation
        this.numbers = nums; // stores in the inputted arraylist of numbers in this class

        // create the first generation
        generateFirstGeneration(nums, puzzles); // helper function that randomly places numbers in the four bins
        super.setPopulation(puzzles.clone()); // sets the population in the superclass

        //Runs the GA algorithm for the given amount of time.
        while (getTime() < time) {
            if (getGenerationSame() < RESTART) //Continues the algorithm if not stuck at plateau
                nextGeneration();
            else { //Random restart with a new set of puzzles if current iteration is stuck
                generateFirstGeneration(nums, puzzles);
                randomRestart(puzzles.clone());
            }
        }
        NumberAllocation bestResult = (NumberAllocation) getLargestParent(); //Gets the best parent

        //Prints the results of the GA
        System.out.println("Largest Score: " + bestResult.getScore());
        System.out.println("Generation Found: " + getGenFound());
        System.out.println("Total Generations Searched: " + getGeneration());
        System.out.println("Bins:");
        System.out.println("Bin 1: " + bestResult.getBin1());
        System.out.println("Bin 2: " + bestResult.getBin2());
        System.out.println("Bin 3: " + bestResult.getBin3());
        System.out.println("Bin 4: " + bestResult.getBin4());


    }

    /**
     *Function for generating the first generation of the genetic algorithm
     * @param startingNums input from the text file that contains the float values
     * @param puzzles array to store all the puzzles we are creating
     */
    public void generateFirstGeneration(ArrayList<Float> startingNums, NumberAllocation[] puzzles) {
        for (int i = 0; i < puzzles.length; i++) {  // loop for the amount of puzzles in this population
            NumberAllocation currPuzzle = new NumberAllocation(startingNums);  // create a new instance of the puzzle
            ArrayList<Float> copyOfFirstGen = (ArrayList<Float>) startingNums.clone();  // create a copy of the arraylist to pull values from it the copy
            for (float j : copyOfFirstGen) {  // loop to that calls a helper function to place values in bins
                boolean placedInBin = false;
                while (!placedInBin) { // a float is placed in a random bin (trys again if the random bin that it was attempted to add to was already filled with 10 floats)
                    placedInBin = putInBin(j, currPuzzle, copyOfFirstGen);
                }
            }
            puzzles[i] = currPuzzle; // set the puzzle with the values placed in it and store it in the array of the total population
        }
    }

    /**
     * helper function that randomly places a float in a bin
     * @param currFloat the float is is placing in a bin
     * @param currPuzzle the puzzle for which the float is being stored within
     * @param copyOfFirstGen the arraylist of all the numbers from the document
     * @return returns true if the float has been successfully placed in a bin
     */
    public boolean putInBin(float currFloat, NumberAllocation currPuzzle, ArrayList<Float> copyOfFirstGen) {
        boolean returnBool = false; // initializes the return boolean
        int rand = (int) (Math.floor(Math.random() * 4)) + 1; // generates a random number from 1 to 4
        switch (rand) {  // switch case for each bin
            case 1:
                if (currPuzzle.getBin1().size() < 10) {  // checks if the bin is already filled to its max of 10
                    currPuzzle.getBin1().add(currFloat); // if not, then add the value to it
                    returnBool = true; // set the return to true
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
        return returnBool; // returns the boolean, if it s false, this will rerun because of the while loop from generateFirstGeneration
    }


    /**
     * Mutation method for this puzzle
     * Two types of mutation that it will do each with a chance of it occurring
     * @param puzzle1 parent 1 first parent that the children will be based on
     * @param puzzle2 parent 2 second parent that the children will be based on
     * @return returns an array of 2 children which are the children of the two parents
     */
    @Override //TODO implement swapping and merging values of parents
    public Puzzle[] mutation(Puzzle puzzle1, Puzzle puzzle2) {
        NumberAllocation nA1 = (NumberAllocation) puzzle1;  // set the input of the first parent to the proper type
        NumberAllocation nA2 = (NumberAllocation) puzzle2;  // set the input for the second parent to the proper type
        NumberAllocation Child1 = new NumberAllocation(((NumberAllocation) puzzle1).getAllNum());  // creates an instance of child1 with empty bins
        NumberAllocation Child2 = new NumberAllocation(((NumberAllocation) puzzle2).getAllNum());  // creates an instance of child2 with empty bins

        // bin1 swap
        ArrayList<Float> Child1Bin1 = new ArrayList<>(); // generates all the bins for each child
        ArrayList<Float> Child2Bin1 = new ArrayList<>();
        ArrayList<Float> Child1Bin2 = new ArrayList<>();
        ArrayList<Float> Child2Bin2 = new ArrayList<>();
        ArrayList<Float> Child1Bin3 = new ArrayList<>();
        ArrayList<Float> Child2Bin3 = new ArrayList<>();
        ArrayList<Float> Child1Bin4 = new ArrayList<>();
        ArrayList<Float> Child2Bin4 = new ArrayList<>();

        double random = new Random().nextDouble(); //Random double from 0 to 1 that will decide which type of mutation will occur
        if (random < 0.1) {  // 10% change of this mutation to occur
            Child1Bin1 = nA1.getBin2();  // the bins will cycle (aka the first bin1 of child1 is parent 2's bin, the second bin of child1 is parent 3's bin... etc...)
            Child1Bin2 = nA1.getBin3();
            Child1Bin3 = nA1.getBin4();
            Child1Bin4 = nA1.getBin1();
            Child2Bin1 = nA2.getBin2();
            Child2Bin2 = nA2.getBin3();
            Child2Bin3 = nA2.getBin4();
            Child2Bin4 = nA2.getBin1();

        } else { // if the random double is above 10% his type of mutation will happen (90% chance)

            int rand = (int) (Math.floor(Math.random() * 10)) + 1; // generate a random number from 0 to 9 which is the index for swapping
            for (int i = 0; i < 10; i++) {
                if (i < rand) {  //places up to the random index from the first parent in the first child and the second parent into the second child
                    Child1Bin1.add(nA1.getBin1().get(i));
                    Child2Bin1.add(nA2.getBin1().get(i));

                    Child1Bin2.add(nA1.getBin2().get(i));
                    Child2Bin2.add(nA2.getBin2().get(i));

                    Child1Bin3.add(nA1.getBin3().get(i));
                    Child2Bin3.add(nA2.getBin3().get(i));

                    Child1Bin4.add(nA1.getBin4().get(i));
                    Child2Bin4.add(nA2.getBin4().get(i));
                } else {  // places the rest after the random index into the child of the opposite parent
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
        }
        Child1.setBin1(Child1Bin1); // sets the bins of the child to be values in each child
        Child2.setBin1(Child2Bin1);

        Child1.setBin2(Child1Bin2);
        Child2.setBin2(Child2Bin2);

        Child1.setBin3(Child1Bin3);
        Child2.setBin3(Child2Bin3);

        Child1.setBin4(Child1Bin4);
        Child2.setBin4(Child2Bin4);

        // Correct mutation conflicts here between children (duplicates existing within a child's bins)
        correctMutationConflict(Child1, Child2);

        // add to the current population array
        Puzzle[] mutationReturn = {Child1, Child2};
        return mutationReturn;
    }


    /**
     * Takes in two children after mutation and adjusts them to be legal, valid children that will allow the algorithm
     * to progress correctly
     * @param child1 first offspring variation of mutation
     * @param child2 second offspring variation of mutation
     */
    private void correctMutationConflict(NumberAllocation child1, NumberAllocation child2) {

        HashMap<Float, Integer> child1Spread;  // initialize 2 hashmaps
        HashMap<Float, Integer> child2Spread;

        boolean conflicted = true;  // assume there are conflicts within the bins

        float swapFrom1;
        float swapFrom2;

        child1Spread = numberAllocationToHashMap(child1);
        child2Spread = numberAllocationToHashMap(child2);

        if (child1Spread.size() != 40 && child2Spread.size() != 40) { // Both children have 40 unique floats (no duplicates)
            // Find number with occurrence == 2 or == 0 (not in hashmap) and officiate swap of 1 of each

            while (conflicted) {
                swapFrom1 = findDuplicate(child1Spread);
                swapFrom2 = findDuplicate(child2Spread);

                if (swapFrom1 != (float) 11 && swapFrom2 != (float) 11) { // Find Duplicate found a value that needs swapping in both children
                    boolean success1 = replaceInChild(child1, swapFrom1, swapFrom2);
                    boolean success2 = replaceInChild(child2, swapFrom2, swapFrom1);
                    if (success1 && success2) {
                        // Adjust the HashMaps so that future iterations do not find the same duplicates again
                        child1Spread.put(swapFrom1, 1);
                        child2Spread.put(swapFrom2, 1);
                        child1Spread.put(swapFrom2, 1);
                        child2Spread.put(swapFrom1, 1);

                    } else {
                        System.out.println("error");
                    }
                }
                if (swapFrom1 == (float) 11 && swapFrom2 == (float) 11) {
                    conflicted = false; // Will see that there is nothing else to fix and will exit the loop
                }

            }

        }
        // it can return in this case because both children have 1 occurrence of each 40 numbers


    }

    /**
     * Creates a hashmap that makes it easier to find duplicates within a child after a crossover mutation
     *
     * @param child
     * @return a Hashmap with key values of float numbers and values of occurence in child
     */
    private HashMap<Float, Integer> numberAllocationToHashMap(NumberAllocation child) {
        HashMap<Float, Integer> spread = new HashMap<>();
        for (int i = 0; i < 10; i++) { // populating hashmap
            if (spread.containsKey(child.getBin1().get(i))) {
                spread.put(child.getBin1().get(i), spread.get(child.getBin1().get(i)) + 1);
            } else {
                spread.put(child.getBin1().get(i), 1);
            }
            if (spread.containsKey(child.getBin2().get(i))) {
                spread.put(child.getBin2().get(i), spread.get(child.getBin2().get(i)) + 1);
            } else {
                spread.put(child.getBin2().get(i), 1);
            }
            if (spread.containsKey(child.getBin3().get(i))) {
                spread.put(child.getBin3().get(i), spread.get(child.getBin3().get(i)) + 1);
            } else {
                spread.put(child.getBin3().get(i), 1);
            }
            if (spread.containsKey(child.getBin4().get(i))) {
                spread.put(child.getBin4().get(i), spread.get(child.getBin4().get(i)) + 1);
            } else {
                spread.put(child.getBin4().get(i), 1);
            }
        }
        return spread;
    }

    /**
     * helper function to find the duplicates in a hashmap
     * @param spread the hashmap containing all the floats in the puzzle
     * @return return float if there are duplicates in the hashmap
     */
    public float findDuplicate(HashMap<Float, Integer> spread) {
        for (float num : numbers) { // loop through all values from the input
            if (spread.containsKey(num)) {  // check if it is contained in the hashmap
                if (spread.get(num) == 2) {  // if it is, check the value associated with, it, which represents how many times its found in the bins
                    return num;
                }
            }
        }
        return (float) 11;
    }

    /**
     * helper function that makes the replacement
     * @param child the puzzle we are replacing the values within
     * @param replace the float we are replacing
     * @param replaceWith the float we are replacing the value with
     * @return returns true if it made the replacement
     */
    public boolean replaceInChild(NumberAllocation child, float replace, float replaceWith) {
        if (child.getBin1().contains(replace)) { // checks which bin the value we need to replace is in
            child.getBin1().add(child.getBin1().indexOf(replace), replaceWith);  // adds the value we want that was previously missing
            int replacementIndexBin1 = child.getBin1().indexOf(replace); // finds the index of the duplicate value
            child.getBin1().remove(replacementIndexBin1); // removes the duplicate value
            return true; // sets the return type to be true alerting the code the swap was made
        } else if (child.getBin2().contains(replace)) {
            child.getBin2().add(child.getBin2().indexOf(replace), replaceWith);
            int replacementIndexBin2 = child.getBin2().indexOf(replace);
            child.getBin2().remove(replacementIndexBin2);
            return true;
        } else if (child.getBin3().contains(replace)) {
            child.getBin3().add(child.getBin3().indexOf(replace), replaceWith);
            int replacementIndexBin3 = child.getBin3().indexOf(replace);
            child.getBin3().remove(replacementIndexBin3);
            return true;
        } else if (child.getBin4().contains(replace)) {
            child.getBin4().add(child.getBin4().indexOf(replace), replaceWith);
            int replacementIndexBin4 = child.getBin4().indexOf(replace);
            child.getBin4().remove(replacementIndexBin4);
            return true;
        } else return false;
    }
}
