import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class NumberAllocationOperation extends DefaultOperation {

    ArrayList<Float> numbers = new ArrayList<>();


    public NumberAllocationOperation(ArrayList<Float> nums, int population, int time) {
        super(population, time);
        NumberAllocation[] puzzles = new NumberAllocation[population];
        this.numbers = nums;
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

        // Correct mutation conflicts here between children

        correctMutationConflict(Child1, Child2);
//        System.out.println(numberAllocationToHashMap(Child1));
//        System.out.println(numberAllocationToHashMap(Child2));
        hasAllNumbers(Child1);
        hasAllNumbers(Child2);

        // what to return?
        return nA1;
    }

    /**
     * Takes in two children after mutation and adjusts them to be legal, valid children that will allow the algorithm
     * to progress correctly
     *
     * @param child1 first offspring variation of mutation
     * @param child2 second offspring variation of mutation
     */
    public void correctMutationConflict(NumberAllocation child1, NumberAllocation child2) {

        HashMap<Float, Integer> child1Spread;
        HashMap<Float, Integer> child2Spread;

        boolean conflicted = true;

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
    public HashMap<Float, Integer> numberAllocationToHashMap(NumberAllocation child) {
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

    public float findDuplicate(HashMap<Float, Integer> spread) {
        for (float num : numbers) {
            if (spread.containsKey(num)) {
                if (spread.get(num) == 2) {
                    return num;
                }
            }
        }

        return (float) 11;
    }

    public boolean replaceInChild(NumberAllocation child, float replace, float replaceWith) {
        if (child.getBin1().contains(replace)) {
            child.getBin1().add(child.getBin1().indexOf(replace), replaceWith);
//            System.out.println("replaced " + replace + " with " + replaceWith + " in " + child.toString());
            return true;
        } else if (child.getBin2().contains(replace)) {
            child.getBin2().add(child.getBin2().indexOf(replace), replaceWith);
//            System.out.println("replaced " + replace + " with " + replaceWith + " in " + child.toString());

            return true;
        } else if (child.getBin3().contains(replace)) {
            child.getBin3().add(child.getBin3().indexOf(replace), replaceWith);
//            System.out.println("replaced " + replace + " with " + replaceWith + " in " + child.toString());

            return true;
        } else if (child.getBin4().contains(replace)) {
            child.getBin4().add(child.getBin4().indexOf(replace), replaceWith);
//            System.out.println("replaced " + replace + " with " + replaceWith + " in " + child.toString());

            return true;
        } else return false;
    }

    public void hasAllNumbers(NumberAllocation child) {
        for (float num : numbers) {
            if (!child.getBin1().contains(num) &&
                    !child.getBin2().contains(num) &&
                    !child.getBin3().contains(num) &&
                    !child.getBin4().contains(num)) {
                System.out.println(child.toString() + " doesn't contain all 40 numbers with size of: " + child.getAllNum().size());
            }

        }
        System.out.println(child.toString() + " Contains all 40 numbers with size of: " + child.getAllNum().size());
    }

}
