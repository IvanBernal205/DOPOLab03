package domain;
import java.awt.Color;


/**
 * Write a description of class Mamut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mamut extends Elephant
{
    public Mamut(EcoSafari habitat, int row, int column){
        super(habitat, row, column);
    }
    
    
    public Color getColor(){
        return new Color(139, 94, 52);
    }
    
    public void tic(){
        if (!hasActed && randomMove()) {
            changeEnergy(-5);
            if (getEnergy()==0){
                disappear();
            }else if(nextToBush()){ // si queda al lado de 3 arbustos recarga energia
                changeEnergy(20);
            }
        }
        hasActed=true;
    }
    
    public boolean nextToBush(){
        Entity[] nb = neighbors();
        int numBush = 0;
        for(Entity e : nb){
            if (e instanceof Bush){
                numBush++;
            }
            if (numBush == 3) return true;
        }
        return false;
    }
    
    public boolean randomMove(){
        int r = (int)(Math.random() * 3) - 1;
        int c = (int)(Math.random() * 3) - 1;
        int cont = 0;
        
        while((r==0 && c==0) || (!move(r,c) && cont<9)){
            r = (int)(Math.random() * 3) - 1;
            c = (int)(Math.random() * 3) - 1;
            cont++;
        }
        if(cont == 9) return false;
        return true;
    }
}