public class Puzzle implements Comparable<Puzzle>, Cloneable{

    /**
     * Gets score of Puzzle
     *
     * @return Score used for fitness
     */
    public float getScore() {
        return 0;
    }

    @Override
    public int compareTo(Puzzle o) {
        float f1 = getScore();
        float f2 = o.getScore();
        /*
        Had to use this instead of f1 - f2 since compareTo() uses ints and compares if the difference is positive, negative,
        or 0 to sort. Since casting a float into an int removes the decimals, it does not always get the sign of the difference
        correctly (such as turning a difference of 0.1 into 0, when it should return a positive number)
         */
        if (f1 == f2) //If equal return 0
            return 0;
        else if (f1 > f2) //If first score is greater than second, return positive number
            return 1;
        else //If difference is negative return a negative number
            return -1;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
