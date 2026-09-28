package domain;
import java.awt.Color;

/**
 * The Leopard class represents a leopard in the EcoSafari simulation.
 *
 * @author César Santiago Malaver Garnica
 * @author Iván Andres Bernal Sabogal
 * @version 27-09-2026
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
    
    /**
     * Returns the color of the leopard based on its energy level.
     * @return the color of the leopard
     */
    public final Color getColor(){
        return (getEnergy()>=40 ? new Color (204, 153, 0):new Color(111, 78, 55));
    }
    
    public final int shape(){
        return Entity.ROUND;
    }
    
    /**
     * Decreases the energy of the leopard by 10. If the energy reaches 0, the leopard disappears.
     */
    public void tic(){
        if (!hasActed){
            changeEnergy(-10);
            if (getEnergy() == 0){
                disappear();
            } else {
                eatOneBush();
                move(-2, -2);
            }
        }
        hasActed = true;
    }

    /**
     * Eats one bush if the leopard is next to it.
     */
    private void eatOneBush(){
        for (Entity n : neighbors()){
            if (n instanceof Bush){
                n.disappear();
                break;
            }
    }
}
    
    public void tac(){
        hasActed = false;
    }
}