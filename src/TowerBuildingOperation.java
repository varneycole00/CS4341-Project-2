import java.util.ArrayList;
import java.util.Random;

public class TowerBuildingOperation extends DefaultOperation {
    public TowerBuildingOperation(ArrayList<Piece> pieces, int population, int time) {
        super(population);
        int RESTART = 10; // Number of generations before random restart
        TowerBuilding[] puzzles = new TowerBuilding[population];
        generateFirstGeneration(pieces, puzzles);
        for(TowerBuilding p: puzzles)
            System.out.println(p.getTower().size()+"\t"+p.getTower());
        super.setPopulation(puzzles);

        while (getTime() < time) {
            if(getGenerationSame() < RESTART) //Continues the algorithm if not stuck at plateau
                nextGeneration();
            else { //Random restart with a new set of puzzles if current iteration is stuck
                generateFirstGeneration(pieces, puzzles);
                randomRestart(puzzles.clone());
            }
        }

        TowerBuilding bestResult = (TowerBuilding) getLargestParent(); //Gets the best parent

        //Prints the results of the GA
        System.out.println("Largest Score: "+bestResult.getScore());
        System.out.println("Generation Found: "+getGenFound());
        System.out.println("Total Generations Searched: "+getGeneration());
    }

    @Override //TODO implement swapping values of parents
    public Puzzle[] mutation(Puzzle puzzle1, Puzzle puzzle2) {
        TowerBuilding nA1 = (TowerBuilding) puzzle1;
        TowerBuilding nA2 = (TowerBuilding) puzzle2;

        Puzzle[] mutationReturn = {puzzle1, puzzle2};
        return mutationReturn;
    }

    public void generateFirstGeneration(ArrayList<Piece> pieces, TowerBuilding[] puzzles){
        Random r= new Random();
        for(int i = 0; i< puzzles.length; i++){
            ArrayList<Piece> temp = (ArrayList<Piece>) pieces.clone();
            TowerBuilding tower = new TowerBuilding(pieces);
            int numPieces = r.nextInt(10)+1;
            for(int j = 0; j < numPieces; j++){
                tower.getTower().add(temp.remove(r.nextInt(temp.size())));
            }
            puzzles[i] = tower;
        }
    }

}
