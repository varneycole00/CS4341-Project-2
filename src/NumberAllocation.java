import java.util.ArrayList;

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
}
