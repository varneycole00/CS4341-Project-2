import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that stores the individual puzzles for puzzle 1
 */
public class NumberAllocation extends Puzzle implements Cloneable {
    private final ArrayList<Float> allNum;   // list of all the numbers found in the input text file
    private ArrayList<Float> bin1 = new ArrayList<>(); // arraylist for storing the first bin
    private ArrayList<Float> bin2 = new ArrayList<>(); // arraylist for storing the second bin
    private ArrayList<Float> bin3 = new ArrayList<>(); // arraylist for storing the third bin
    private ArrayList<Float> bin4 = new ArrayList<>(); // arraylist for storing the fourth bin

    /**
     * Constructor for the class
     * @param arrayList takes in the list of all the floats in the text file input
     */
    public NumberAllocation(ArrayList<Float> arrayList) {  // constructor for this class
        super();
        allNum = arrayList;  // sets the list of all the numbers to be
    }

    /**
     * Helper function to calculate the score of bin1
     * @return returns a float which is the product all the floats in the first bin together
     */
    public float bin1Score(){
        float firstBin = 1;
        for (float f : bin1) //First bin is product
            firstBin *= f;
        return firstBin;
    }

    /**
     * Helper function for to calculate the score of bin2
     * @return a float which is the sum of all the numbers together
     */
    public float bin2Score(){
        float secondBin = 0;
        for (float f : bin2) //Second bin is sum
            secondBin += f;
        return secondBin;
    }

    /**
     * a helper function to calculate the score of bin3
     * @return a float which represents the smallest number subtracted from the largest
     */
    public float bin3Score(){
        ArrayList<Float> clone = (ArrayList<Float>) bin3.clone(); // copy bin3
        clone.sort(Float::compareTo); // sort the bin from smallest to largest
        return clone.get(clone.size() - 1) - clone.get(0); //get the smallest and largest and subtract them
    }

    /**
     * a helper function to calculate the score of bin3
     * @return 0, due to this bin being ignored
     */
    public float bin4Score(){
        return 0;
    }

    /**
     * Override of the getScore() due to each puzzle scoring differently
     * @return returns the sum from the four helper functions above
     */
    @Override
    public float getScore() {
        return bin1Score()+bin2Score()+bin3Score()+bin4Score();
    }

    // getters and setters
    public ArrayList<Float> getAllNum() {
        return allNum;
    }

    public ArrayList<Float> getBin1() {
        return bin1;
    }

    public void setBin1(ArrayList<Float> bin1) {
        this.bin1 = bin1;
    }

    public ArrayList<Float> getBin2() {
        return bin2;
    }

    public void setBin2(ArrayList<Float> bin2) {
        this.bin2 = bin2;
    }

    public ArrayList<Float> getBin3() {
        return bin3;
    }

    public void setBin3(ArrayList<Float> bin3) {
        this.bin3 = bin3;
    }

    public ArrayList<Float> getBin4() {
        return bin4;
    }

    public void setBin4(ArrayList<Float> bin4) {
        this.bin4 = bin4;
    }

    /**
     * Override of the equals method
     * @param o takes in a object to check if they are equal
     * @return return true if the two puzzles are identical
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // checks if the objects are both the same by where it is stored in memory
        if (o == null || getClass() != o.getClass()) return false; // if the class of the objects are different they are not the same
        NumberAllocation that = (NumberAllocation) o; // sets this class to be an object
        return Objects.equals(allNum, that.allNum) && Objects.equals(bin1, that.bin1) && Objects.equals(bin2, that.bin2) && Objects.equals(bin3, that.bin3) && Objects.equals(bin4, that.bin4); // lastly checks if all the values in each bin are identical
    }

    /**
     * Override of the hashcode generation method
     * @return returns an int that takes into consideration the values of each of the bins
     */
    @Override
    public int hashCode() {
        return Objects.hash(allNum, bin1, bin2, bin3, bin4);
    }
}
