package domain;
import java.awt.Color;

/**
 * A bush that can be used in the EcoSafari
 * 
 * @author Ivan Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica 
 * @version 26-09-2026
 */
public class Bush extends Organism implements Entity{
    private final EcoSafari habitat;
    private boolean hasActed;

    public Bush(EcoSafari habitat, int row, int column){
        this.habitat = habitat;
        habitat.set((Entity)this, row, column);  
        hasActed=false;
    }
    
    /**
     * Decreases the energy of the bush by 10. If the energy reaches 0, the bush disappears.
     * After 2 tics the bush spreads to an adjacent empty cell.
     */
    public void tic(){
        if (!hasActed) {
            changeEnergy(-10);
            if (getEnergy()==0){
                disappear();
            }else if(getEnergy() == 80){
                spread();
            }
        }
        hasActed=true;
    }
    
    /**
     * If there is an elephant in the neighborhood, the bush disappears.
     */
    public void tac(){
        for (Entity neighbor : neighbors()){ 
            if (neighbor instanceof Elephant){
                disappear();
                break;
            }
        }
        hasActed=false;
    }    
    
    /**
     * Returns the color of the bush based on its energy level.
     * If the energy is greater than 60, it is green below that the bush is yellow.
     * @return the color of the bush
     */
    public Color getColor(){
        return(getEnergy()>60 ? Color.green : Color.yellow);
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }

    /**
     * Spreads the bush to an adjacent empty cell
     */
    private void spread(){
        int[] position = getHabitat().find(this);
        int r = position[0];
        int c = position[1];
        
        if (getHabitat().isInside(r-1,c) && getHabitat().get(r-1,c) == null){//norte
            r = r-1; 
        }
        else if (getHabitat().isInside(r+1,c) && getHabitat().get(r+1,c) == null){//sur
            r = r+1;
        }
        else if (getHabitat().isInside(r,c+1) && getHabitat().get(r,c + 1) == null){//este
            c = c+1;
        }
        else if (getHabitat().isInside(r,c-1) && getHabitat().get(r,c-1) == null){//oeste
            c = c-1;
        }else{
            return;
        }

        Bush newBush = new Bush(getHabitat(), r, c);
        newBush.hasActed = true; // evita que el nuevo arbusto baje de energia recien se crea
    }
}