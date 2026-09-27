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
    
    public void tic(){
        if (!hasActed) {
            changeEnergy(-10);
            if (getEnergy()==0){
                disappear();
            }else if(getEnergy() == 80){
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
                    hasActed=true;
                    return;
                }
                Bush newBush = new Bush(getHabitat(), r, c);
                newBush.hasActed = true; // evita que el nuevo arbusto baje de energia recien se crea
            }
        }
        
        hasActed=true;
    }
    
    public void tac(){
        for (Entity neighbor : neighbors()){ 
            if (neighbor instanceof Elephant){
                disappear();
                break;
            }
        }
        hasActed=false;
    }    
    
    
    public Color getColor(){
        return(getEnergy()>60 ? Color.green : Color.yellow);
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }
}