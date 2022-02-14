import java.util.ArrayList;
import java.util.Random;

public class TowerBuildingOperation extends DefaultOperation {
    private ArrayList<Piece> pieces;

    public TowerBuildingOperation(ArrayList<Piece> pieces, int population, int time) {
        super(population);
        this.pieces = pieces;
        int RESTART = 10000; // Number of generations before random restart
        TowerBuilding[] puzzles = new TowerBuilding[population];
        generateFirstGeneration(pieces, puzzles); //Creates first generation
        super.setPopulation(puzzles);

        while (getTime() < time) { //Makes sure algorithm is running for correct number of seconds
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
        System.out.println("Tower: ");
        System.out.println(bestResult.getTower());

    }

    @Override
    public Puzzle[] mutation(Puzzle puzzle1, Puzzle puzzle2) {
        TowerBuilding nA1 = (TowerBuilding) puzzle1; //First parent
        TowerBuilding nA2 = (TowerBuilding) puzzle2; //Second parent
        ArrayList<Piece> tower1 = nA1.getTower(); //Tower for first parent
        ArrayList<Piece> tower2 = nA2.getTower(); //Tower for second parent
        ArrayList<Piece> child1 = new ArrayList<>(); //Tower for first child
        ArrayList<Piece> child2 = new ArrayList<>(); //Tower for second child
        Random r = new Random();
        int random = r.nextInt(2);
        Piece p = null; //Initializes temp Piece

        switch(random){ //Chooses the type of mutation randomly
            case 0: //Half-Half swap
                for(int i = 0; i < tower1.size() / 2; i++) //First half of parent 1 into child 1
                    child1.add(tower1.get(i));
                for(int i = 0; i < tower2.size() / 2; i++) //First half of parent 2 into child 2
                    child2.add(tower2.get(i));
                for(int i = tower2.size() / 2; i < tower2.size(); i++){ //Second half of parent 2 into child 1
                    if(!child1.contains(tower2.get(i)))
                        child1.add(tower2.get(i));
                }
                for(int i = tower1.size() / 2; i < tower1.size(); i++){ //Second half of parent 1 into child 2
                    if(!child2.contains(tower1.get(i)))
                        child2.add(tower1.get(i));
                }
                break;
            case 1: //Random inserts
                child1 = (ArrayList<Piece>) tower1.clone(); //Child 1 duplicates Parent 1
                child2 = (ArrayList<Piece>) tower2.clone(); //Child 2 duplicates Parent 2
                p = null;
                int iChild1Size = child1.size(); //Child 1 initial size
                int iChild2Size = child2.size(); //Child 2 intitial starting


                for(int i = 0; i<(pieces.size() - iChild1Size)/2; i++){ //Inserts pieces into child 1 that it doesn't have
                    do {
                        p = pieces.get(r.nextInt(pieces.size()));
                    } while(child1.contains(p)); //Makes sure pieces added aren't already in child 1
                    if(child1.size()>0)
                        child1.add(r.nextInt(child1.size()), p); //Inserts piece randomly into child 1
                    else child1.add(p);
                }

                for(int i = 0; i<(pieces.size() - iChild2Size)/2; i++){ //Inserts pieces into child 2 that it doesn't have
                    do {
                        p = pieces.get(r.nextInt(pieces.size()));
                    } while(child2.contains(p)); //Makes sure pieces added aren't already in child 2
                    if(child2.size()>0)
                        child2.add(r.nextInt(child2.size()), p); //Inserts piece randomly into child 2
                    else child2.add(p);
                }
                break;
            case 2: //Singular insert
                child1 = (ArrayList<Piece>) tower1.clone(); //Child 1 duplicates Parent 1
                child2 = (ArrayList<Piece>) tower2.clone(); //Child 2 duplicates Parent 2
                p = pieces.get(r.nextInt(pieces.size()));

                if(child1.size()<pieces.size()) { //Only adds piece if child isn't at max size
                    do {
                        p = pieces.get(r.nextInt(pieces.size()));
                    } while (child1.contains(p));  //Makes sure pieces added aren't already in child 1
                    if (child1.size() > 0)
                        child1.add(r.nextInt(child1.size()), p); //Inserts piece randomly into child 1
                    else child1.add(p);
                }


                if(child2.size()<pieces.size()) { //Only adds piece if child isn't at max size
                    do {
                        p = pieces.get(r.nextInt(pieces.size()));
                    } while (child2.contains(p)); //Makes sure pieces added aren't already in child 2
                    if (child2.size() > 0)
                        child2.add(r.nextInt(child2.size()), p); //Inserts piece randomly into child 2
                    else child2.add(p);

                }

                break;
        }
        TowerBuilding first = new TowerBuilding(pieces);
        first.setTower(child1); //Creates first child with mutated tower pieces
        TowerBuilding second = new TowerBuilding(pieces);
        second.setTower(child2); //Creates second child with mutated tower pieces
        Puzzle[] mutationReturn = {first, second};
        return mutationReturn;
    }

    /**
     * Generates the first generation of pieces
     * @param pieces Pieces in list that can be used to make puzzles
     * @param puzzles Puzzle array to add the puzzles into
     */
    private void generateFirstGeneration(ArrayList<Piece> pieces, TowerBuilding[] puzzles){
        Random r= new Random();
        for(int i = 0; i< puzzles.length; i++){
            ArrayList<Piece> temp = (ArrayList<Piece>) pieces.clone();
            TowerBuilding tower = new TowerBuilding(pieces);
            int numPieces = r.nextInt(pieces.size())+1;
            for(int j = 0; j < numPieces; j++){
                tower.getTower().add(temp.remove(r.nextInt(temp.size())));
            }
            puzzles[i] = tower;
        }
    }

}