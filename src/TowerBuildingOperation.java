import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TowerBuildingOperation extends DefaultOperation {
    private ArrayList<Piece> pieces;

    public TowerBuildingOperation(ArrayList<Piece> pieces, int population, int time) {
        super(population);
        this.pieces = pieces;
        int RESTART = 10; // Number of generations before random restart
        TowerBuilding[] puzzles = new TowerBuilding[population];
        generateFirstGeneration(pieces, puzzles);
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
        for(Puzzle p: getPopulation()){
            TowerBuilding tb = (TowerBuilding) p;
            System.out.println(tb.getTower());
        }
        TowerBuilding best = (TowerBuilding) bestResult;
        //Prints the results of the GA
        System.out.println("Largest Score: "+bestResult.getScore());
        System.out.println("Generation Found: "+getGenFound());
        System.out.println("Total Generations Searched: "+getGeneration());
        System.out.println("Tower: ");
        System.out.println(bestResult.getTower());

    }

    @Override //TODO implement swapping values of parents
    public Puzzle[] mutation(Puzzle puzzle1, Puzzle puzzle2) {
        TowerBuilding nA1 = (TowerBuilding) puzzle1;
        TowerBuilding nA2 = (TowerBuilding) puzzle2;
        ArrayList<Piece> tower1 = nA1.getTower();
        ArrayList<Piece> tower2 = nA2.getTower();
        ArrayList<Piece> child1 = new ArrayList<>();
        ArrayList<Piece> child2 = new ArrayList<>();
        Random r = new Random();
        int random = r.nextInt(2);
        random=1; //TODO here to test pure half/half only
        switch(random){
            case 0: //Half-Half swap
                for(int i = 0; i < tower1.size() / 2; i++)
                    child1.add(tower1.get(i));
                for(int i = 0; i < tower2.size() / 2; i++)
                    child2.add(tower2.get(i));
                for(int i = tower2.size() / 2; i < tower2.size(); i++){
                    if(!child1.contains(tower2.get(i)))
                        child1.add(tower2.get(i));
                }
                for(int i = tower1.size() / 2; i < tower1.size(); i++){
                    if(!child2.contains(tower1.get(i)))
                        child2.add(tower1.get(i));
                }
                break;
            case 1: //Random inserts
                child1 = (ArrayList<Piece>) tower1.clone();
                child2 = (ArrayList<Piece>) tower2.clone();
                for(Piece p: child2)
                    if(!child1.contains(p)) {
                        if(child1.size()>0)
                        child1.add(r.nextInt(child1.size()), p);
                        else child1.add(p);
                    }
                for(Piece p: child1)
                    if(!child2.contains(p)) {
                        if(child2.size()>0)
                            child2.add(r.nextInt(child2.size()), p);
                        else child2.add(p);
                    }
                break;
            case 2:
                break;
        }
        TowerBuilding first = new TowerBuilding(pieces);
        first.setTower(child1);
        TowerBuilding second = new TowerBuilding(pieces);
        first.setTower(child2);
        Puzzle[] mutationReturn = {first, second};
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
