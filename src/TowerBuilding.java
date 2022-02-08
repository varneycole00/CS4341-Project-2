import java.util.ArrayList;

public class TowerBuilding {
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> order;

    public TowerBuilding(ArrayList<Piece> pieces){
        this.pieces = pieces;
    }

    public int getScore(){
        if(order.get(0).getType()!=Type.DOOR || order.get(order.size()-1).getType()!=Type.LOOKOUT) //Rule 1 and 2
            return 0;
        int weight;
        for(int i = order.size()-2; i >=0 ; i--){
            weight = order.size()-i-1;
            if(i!=0 && order.get(i).getType()!=Type.WALL) //Rule 3
                return 0;
            if(order.get(i+1).getWidth()>=order.get(i).getWidth()) //Rule 4
                return 0;
            if(order.get(i).getStrength()<weight) //Rule 5
                return 0;
        }

        int cost = 0;
        for(Piece p: order)
            cost+=p.getCost();

        return 10+order.size()-cost;

    }


}
