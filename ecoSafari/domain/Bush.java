package domain;
import java.awt.Color;

/**
 * Write a description of class Bush here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
            }
            
            if(getEnergy() == 80){
                int[] position = getHabitat().find(this);
                int r = position[0];
                int c = position[1];
                
                if (getHabitat().get(r-1,c) == null){//norte
                    Bush b = new Bush(getHabitat(), r-1, c);
                    getHabitat().set(b, r-1,c);
                }
                else if (getHabitat().get(r+1,c) == null){//sur
                    Bush b = new Bush(getHabitat(), r+1, c);
                    getHabitat().set(b, r+1,c);
                }
                else if (getHabitat().get(r,c + 1) == null){//este
                    Bush b = new Bush(getHabitat(), r, c+1);
                    getHabitat().set(b, r,c+1);
                }
                else if (getHabitat().get(r,c-1) == null){//oeste
                    Bush b = new Bush(getHabitat(), r, c-1);
                    getHabitat().set(b, r,c-1);
                }
            }
        }
        
        
        hasActed=true;
    }
    
    public void tac(){
        hasActed=false;
    }    
    
    
    public Color getColor(){
        return(getEnergy()>=60? Color.green: Color.yellow);
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }
}