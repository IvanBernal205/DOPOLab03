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
        if(!hasActed){
            if (getEnergy() == 0) disappear();
            Entity[] neighbors = this.neighbors();
            for (Entity n : neighbors){
                if (n instanceof Grass) {
                    n.disappear();
                    changeEnergy(10);
                    break;
                }
            }
        }
        if (move(-2,-2)); //Revisar tema de energia
        if (getEnergy() == 0) disappear();
        hasActed = true;
    }
    
    public void tac(){
        hasActed = false;
    }
}
