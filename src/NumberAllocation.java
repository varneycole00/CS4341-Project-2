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

    public float bin1Score(){
        float firstBin = 1;
        for (float f : bin1) //First bin is product
            firstBin *= f;
        return firstBin;
    }
    
    public float bin2Score(){
        float secondBin = 0;
        for (float f : bin2) //Second bin is sum
            secondBin += f;
        return secondBin;
    }
    
    public float bin3Score(){
        ArrayList<Float> clone = (ArrayList<Float>) bin3.clone();
        clone.sort(Float::compareTo);
        return clone.get(clone.size() - 1) - clone.get(0);//Third bin is max-min
    }

    public float bin4Score(){
        return 0;
    }
    
    @Override
    public float getScore() {
        return bin1Score()+bin2Score()+bin3Score()+bin4Score();
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
