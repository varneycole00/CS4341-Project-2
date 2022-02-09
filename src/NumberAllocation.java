import java.util.ArrayList;
import java.util.Arrays;

public class NumberAllocation extends Puzzle implements Cloneable {
    private final ArrayList<Float> allNum;
    private ArrayList<Float> bin1;
    private ArrayList<Float> bin2;
    private ArrayList<Float> bin3;
    private ArrayList<Float> bin4;

    public NumberAllocation(ArrayList<Float> arrayList) {
        super();
        allNum = arrayList;
    }

    @Override
    public float getScore() {
        float firstBin = 1, secondBin = 0, thirdBin;
        for (float f : bin1) //First bin is product
            firstBin *= f;
        for (float f : bin2) //Second bin is sum
            secondBin += f;

        bin3.sort(Float::compareTo);
        thirdBin = bin3.get(bin3.size() - 1) - bin3.get(0);//Third bin is max-min

        return firstBin + secondBin + thirdBin;
    }
}
