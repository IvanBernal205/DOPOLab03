package domain;
import java.awt.Color;

/**
 * Write a description of class Zebra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zebra extends Organism implements Entity{
    private final EcoSafari habitat;
    private boolean hasActed;
    
    /**
     * Creates a new zebra
     */
    public Zebra(EcoSafari habitat, int row, int column){
        this.habitat = habitat;
        habitat.set((Entity)this, row, column);
        hasActed = false;
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }
    
    public final Color getColor(){
        return Color.LIGHT_GRAY;
    }
    
    public final int shape(){
        return Entity.ROUND;
    }
    
    public void tic(){
        int[] position = getHabitat().find(this);
        int r = position[0];
        int c = position[1];
        if(!hasActed){
            int[] delta = randomDelta();
            int cont = 0;
            boolean moved = move(delta[0], delta[1]);
            
            while(!moved && cont<8){
                delta = randomDelta();
                moved = move(delta[0], delta[1]);
                cont++;
            }
            if (moved){
                changeEnergy(-(getEnergy() * 0.10f));
            }
            if (getEnergy()==0){
                replaceWithEarth(this);
                return;
            }

            Entity[] neighbors = this.neighbors();
            for (Entity n : neighbors){
                if (n instanceof Grass) {
                    int[] positionG = getHabitat().find(n);
                    n.disappear();
                    new Earth(getHabitat(), positionG[0], positionG[1]);
                    this.changeEnergy(10);
                    break;
                }
            }
        }
        hasActed = true;
    }
    
    public void tac(){
        hasActed = false;
    }
    
    private int[] randomDelta(){
        int randR, randC;
        do{
            randR = ((int) (Math.random() * 3) - 1) * 2;
            randC = ((int) (Math.random() * 3) - 1) * 2;
        }while(randR == 0 && randC == 0);
        return new int[] {randR, randC};
    }

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
    
    private void replaceWithEarth(Entity e){
        int[] position = habitat.find(e);
        if (position != null){
            new Earth(habitat, position[0], position[1]);
        }
    }
}
