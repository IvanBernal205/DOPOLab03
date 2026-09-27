package domain;
import java.awt.Color;

/**
 * Write a description of class Storm here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Storm implements Entity{
    private EcoSafari habitat;
    private boolean hasActed;
    private boolean isCenter;
    
    /**
     * @param row Center of the storm.
     */
    public Storm(EcoSafari habitat, int row, int column){
        this.habitat = habitat;
        habitat.set((Entity) this, row, column);
        isCenter = true;
        createAround(row, column);
        hasActed = false;
    }
    
    public Storm(EcoSafari habitat, int row, int column, boolean isCenter){
        this.habitat = habitat;
        habitat.set((Entity) this, row, column);
        isCenter = false;
        hasActed = false;
    }
    
    public void tic(){ //La tormenta aun no tiene la logica correcta de la eliminacion
        if(!isCenter){
            disappear();
            return;
        }
        if (!hasActed && move(-1, 1));
        hasActed = true;
    }
    
    public void tac(){
        if (!isCenter) return;
        int [] position = getHabitat().find(this);
        if (position == null) return;
        createAround(position[0], position[1]);
        hasActed = false;
    }
    
    public Color getColor(){
        return (isCenter ? Color.black : Color.gray);
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }
    
    private void createAround(int row, int column){
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0) continue;
                if (getHabitat().get(row + i, column + j) != null) continue;
                new Storm(getHabitat(), row + i, column + j, false);
            }
        }
    }

    public boolean move(int deltaRows, int deltaColumns){
    int[] position = habitat.find(this);
    boolean ok = false;
    if (position != null){
        int r = position[0] + deltaRows;
        int c = position[1] + deltaColumns;
        if (habitat.isInside(r, c)){
            habitat.set(null, position[0], position[1]);
            habitat.set(this, r, c);
            ok = true;
        }
    }
    return ok;
    }
}