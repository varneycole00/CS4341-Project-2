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
        if (f1 == f2)
            return 0;
        else if (f1 > f2)
            return 1;
        else
            return -1;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
