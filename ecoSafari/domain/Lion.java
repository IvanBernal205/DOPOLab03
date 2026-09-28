package domain;
import java.awt.Color;


/**
 * Write a description of class Lion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lion extends Organism implements Entity
{
    private EcoSafari habitat;
    private boolean hasActed;

    public Lion(EcoSafari habitat, int row, int column){
        this.habitat = habitat;
        habitat.set((Entity)this, row, column);
        hasActed = false;
    }

    /**
     * Decreases the energy of the lion by 10%. If the energy reaches 0, the lion disappears.
     * The lion moves to a random adjacent cell. If it is next to a zebra, it eats it and gains 50% energy.
     * If it is next to another lion and there is an empty cell next to them, they reproduce and create a new lion in the empty cell.
     */
    public void tic(){ 
        if(!hasActed){
            hasActed = true;
            int[] delta = randomDelta();
            int cont = 0;
            boolean moved = move(delta[0], delta[1]);
            while(!moved && cont<8){
                delta = randomDelta();
                moved = move(delta[0], delta[1]);
                cont++;
            }
            if (moved){
                changeEnergy(-0.10f);
                if (getEnergy()==0){
                    replaceWithEarth(this);
                    return;
                }
            }
            eat();
            reproduce();
        }
    }

    public void tac(){
        hasActed=false;
    }

    /**
     * Generates a move.
     * @return an array containing the row and column changes
     */
    private int[] randomDelta(){
        int randR, randC;
        do{
            randR = (int) (Math.random() * 3) - 1;
            randC = (int) (Math.random() * 3) - 1;
        }while(randR == 0 && randC == 0);
        return new int[] {randR, randC};
    }

    /**
     * Eats a zebra if the lion is next to it.
     */
    private void eat(){
        for (Entity n : neighbors()){
            if (n instanceof Zebra){
                replaceWithEarth(n);
                changeEnergy(0.50f);
                break;
            }
        }
    }

    /**
     * Reproduces a new lion if the lion is next to another lion and there is an empty cell next to them.
     */
    private void reproduce(){
        int[] position = habitat.find(this);
        if (position == null) return;
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0) continue;
                int r = position[0] + i;
                int c = position[1] + j;
                if ((habitat.get(r, c) instanceof Earth || habitat.get(r, c) instanceof Grass) && habitat.get(r + i, c + j) instanceof Lion){
                    Lion cub = new Lion(habitat, r, c);
                    cub.hasActed = true; // evita que la cria actue en el mismo tic en que nace
                    return;
                }
            }
        }
    }

    /**
     * Replaces the entity with an earth entity in the same position.
     * @param e the entity to be replaced
     */
    private void replaceWithEarth(Entity e){
        int[] position = habitat.find(e);
        if (position != null){
            new Earth(habitat, position[0], position[1]);
        }
    }

    /**
     * Moves the lion to a new position if it is empty or contains earth or grass.
     * @param deltaRows the change in rows
     * @param deltaColumns the change in columns
     * @return true if the move was successful, false otherwise
     */
    public boolean move(int deltaRows, int deltaColumns){
        int[] position = habitat.find(this);
        boolean ok = false;
        if (position != null){
            int r = position[0] + deltaRows;
            int c = position[1] + deltaColumns;
            Entity target = habitat.get(r, c);
            if (target instanceof Earth || target instanceof Grass){
                habitat.set(this, r, c);
                habitat.set(target, position[0], position[1]);
                ok = true;
            }
        }
        return ok;
    }
    
    public Color getColor(){
        return new Color(210, 170, 100); // color león
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }
}