import java.util.ArrayList;
import java.util.Objects;

public class TowerBuilding extends Puzzle implements Cloneable {
    private final ArrayList<Piece> pieces; //All pieces from .txt file
    private ArrayList<Piece> tower; //Pieces in the current tower

    /**
     * Creates a new instance of the Tower Building puzzle
     * @param pieces Pieces that can be used
     */
    public TowerBuilding(ArrayList<Piece> pieces) {
        super();
        this.pieces = pieces;
        tower = new ArrayList<>();
    }

    @Override
    public float getScore() {
        if(tower.size()==0) //Makes sure tower has pieces in it to check
            return 0;
        else {
            if (tower.get(0).getType() != PieceType.DOOR || tower.get(tower.size() - 1).getType() != PieceType.LOOKOUT) //Rule 1 and 2
                return 0;
            int weight; //Weight above a piece

            for (int i = tower.size() - 2; i >= 0; i--) {
                weight = tower.size() - i - 1; //Weight is total number of pieces above a piece
                if (i != 0 && tower.get(i).getType() != PieceType.WALL) //Rule 3
                    return 0;
                if (tower.get(i + 1).getWidth() > tower.get(i).getWidth()) //Rule 4
                    return 0;
                if (tower.get(i).getStrength() < weight) //Rule 5
                    return 0;
            }

            int cost = 0; //Cost of tower
            for (Piece p : tower)
                cost += p.getCost(); //Gets total cost of tower if legal

            return 10 + tower.size()*tower.size() - cost; //Given from assignment
        }
    }

    /**
     * @return Pieces in the current tower
     */
    public ArrayList<Piece> getTower() {
        return tower;
    }

    /**
     * @param tower New tower to replace old one
     */
    public void setTower(ArrayList<Piece> tower) {
        this.tower = tower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TowerBuilding that = (TowerBuilding) o;
        return Objects.equals(pieces, that.pieces) && Objects.equals(tower, that.tower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces, tower);
    }
}
