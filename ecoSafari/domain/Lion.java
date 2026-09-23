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
    }
    
    public void tic(){ // Si un pasto se genera a donde se va a mover el leon desaparece
        int[] position = getHabitat().find(this);
        int r = position[0];
        int c = position[1];
        
        int randR = (int) (Math.random() * 3) - 1;
        int randC = (int) (Math.random() * 3) - 1;
        
        if(!hasActed){
            int cont = 0;
            while(!(move(randR, randC)) && cont<8){
                randR = (int) (Math.random() * 3) - 1;
                randC = (int) (Math.random() * 3) - 1;
                cont++;
            }
            changeEnergy(-10);
            if (getEnergy()==0){
                disappear();
                new Earth(getHabitat(), randR, randC);
            }
            new Earth(getHabitat(), r, c);
        }
        hasActed=true;
    }
    
    public void tac(){
        hasActed=false;
    } 
    
    public Color getColor(){
        return new Color(210, 170, 100); // color león
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }
}