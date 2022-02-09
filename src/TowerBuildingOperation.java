import java.util.ArrayList;

public class TowerBuildingOperation extends IOperation {
    public TowerBuildingOperation(ArrayList<Piece> pieces, int population, int time) {
        super(population, time);
        TowerBuilding[] puzzles = new TowerBuilding[population];
        super.setPopulation(puzzles);
    }

    @Override //TODO implement swapping values of parents
    public Puzzle mutation(Puzzle puzzle1, Puzzle puzzle2) {
        TowerBuilding nA1 = (TowerBuilding) puzzle1;
        TowerBuilding nA2 = (TowerBuilding) puzzle2;
        return null;
    }
}
