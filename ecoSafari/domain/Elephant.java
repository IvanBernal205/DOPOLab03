package domain;
import java.awt.Color;


//Include the documentation
/**
 * The Elephant class represents an elephant in the EcoSafari simulation. 
 * 
 * @author Ivan Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica 
 * @version 26-09-2026
 */
public class Elephant extends Organism implements Entity{
    private final EcoSafari habitat;
    protected boolean hasActed;
    
    /**
     * Constructs an Elephant object.
     * @param habitat the EcoSafari habitat
     * @param row the row position of the elephant
     * @param column the column position of the elephant
     */
    public Elephant(EcoSafari habitat,int row, int column){
        this.habitat=habitat;
        habitat.set((Entity)this, row, column);  
        hasActed=false;
    }

    /**
     * Get the habitat of the elephant.
     * @return the EcoSafari habitat
     */
    public EcoSafari getHabitat(){
        return habitat;
    }
    
    /**
     * Get the color of the elephant based on its energy level.
     * @return the color of the elephant
     */
    public Color getColor(){
        return(getEnergy()>=80? Color.DARK_GRAY: Color.LIGHT_GRAY);
    }

    /**
     * Get the shape of the elephant.
     * @return the shape of the elephant
     */
    public final int shape(){
        return Entity.ROUND;
    }

    /**
     * Performs the tic action for the elephant.
     */
    public void tic(){
        if ((! hasActed) && (move(1, 1))) {
            changeEnergy(-10);
            if (getEnergy()==0){
                disappear();
            }
        }
        hasActed=true;
    }
    
    /**
     * Performs the tac action for the elephant.
     */
    public void tac(){
        hasActed=false;
    }    
}
