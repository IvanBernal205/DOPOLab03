package domain;
import java.awt.Color;


/**
 * The Mamut
 *
 * @author César Santiago Malaver Garnica
 * @author Iván Andres Bernal Sabogal
 * @version 27-09-2026
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
    
    /**
     * Returns whether the mamut is next to 3 bushes or not.
     */
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
    
    /**
     * Moves the mamut to a random adjacent cell.
     * @return true if the mamut was successfully moved, false otherwise
     */
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