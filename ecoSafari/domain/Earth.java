package domain;
import java.awt.Color;


/**
 * The Earth class represents a patch of earth in the EcoSafari simulation. 
 * It can generate grass with a certain probability.
 * @author Ivan Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica 
 * @version 26-09-2026
 */
public class Earth implements Entity
{
    private EcoSafari habitat;
    
    public Earth(EcoSafari habitat, int row, int column){
        this.habitat = habitat;
        habitat.set((Entity)this, row, column);
    }
    
    public void tic(){

    }
    
    /**
     * With a probability of 1/10, the earth generates a grass in its position.
     */
    public void tac(){
        int randNum = (int) (Math.random() * 10) + 1;
        if(randNum == 1){
            int[] position = getHabitat().find(this);
            if (position == null) return;
            int r = position[0];
            int c = position[1];
            new Grass(getHabitat(), r, c);
        }  
    }
    
    public Color getColor(){
        return new Color(158, 110, 62); // Color cafe tierra
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }
}