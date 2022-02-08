import java.util.ArrayList;

public class NumberAllocation implements Cloneable{
    private ArrayList<Float> allNum;
    private ArrayList<Float> bin1;
    private ArrayList<Float> bin2;
    private ArrayList<Float> bin3;
    private ArrayList<Float> bin4;

    public NumberAllocation(ArrayList<Float> arrayList){
        allNum = arrayList;
    }

    public float getScore(){
        float firstBin=1, secondBin = 0, thirdBin;
        for(float f: bin1)
            firstBin*=f;
        for(float f:bin2)
            secondBin+=f;
        bin3.sort(Float::compareTo);
        thirdBin = bin3.get(bin3.size()-1)-bin3.get(0);
        return firstBin+secondBin+thirdBin;
    }
}
