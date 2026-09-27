package domain;
import java.awt.Color;

/**
 * Write a description of class Leopard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Leopard extends Organism implements Entity{
    private final EcoSafari habitat;
    private boolean hasActed;
    
    /**
     * Creates a new leopard
     */
    public Leopard(EcoSafari habitat, int row, int column){
        this.habitat = habitat;
        habitat.set((Entity)this, row, column);
        hasActed = false;
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }
    
    public final Color getColor(){
        return (getEnergy()>=40 ? new Color (204, 153, 0):new Color(111, 78, 55));
    }
    
    public final int shape(){
        return Entity.ROUND;
    }
    
    public void tic(){
        if(!hasActed){
            changeEnergy(-10);
            if (getEnergy() == 0) disappear();
            Entity[] neighbors = this.neighbors();
            for (Entity n : neighbors){
                if (n instanceof Bush) {
                    n.disappear();
                    break;
                }
            }
        }
        if (move(-2,-2));
        hasActed = true;
    }
    
    public void tac(){
        hasActed = false;
    }
}