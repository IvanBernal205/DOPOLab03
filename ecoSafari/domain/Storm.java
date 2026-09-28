package domain;
import java.awt.Color;

/**
 * A storm that can be used in the EcoSafari
 *
 * @author Ivan Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 26-09-2026
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
        this.isCenter = true;
        createAround(row, column);
        hasActed = false;
    }
    
    public Storm(EcoSafari habitat, int row, int column, boolean isCenter){
        this.habitat = habitat;
        habitat.set((Entity) this, row, column);
        this.isCenter = false;
        hasActed = false;
    }
    
    public void tic(){ 
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
    
    /**
     * Creates a storm in the adjacent cells of the given position.
     * @param row the row of the center of the storm
     * @param column the column of the center of the storm
     */
    private void createAround(int row, int column){
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0) continue;
                if (getHabitat().get(row + i, column + j) != null) continue;
                new Storm(getHabitat(), row + i, column + j, false);
            }
        }
    }

    /**
     * Moves the storm.
     * @param deltaRows the change in row position
     * @param deltaColumns the change in column position
     * @return true if the storm was successfully moved, false otherwise
     */
    public boolean move(int deltaRows, int deltaColumns){
        int[] position = habitat.find(this);
        boolean ok = false;
        if (position != null){
            int size = habitat.getSize();
            int r = (position[0] + deltaRows + size) % size;
            int c = (position[1] + deltaColumns + size) % size;
            habitat.set(null, position[0], position[1]);
            habitat.set(this, r, c);
            ok = true;
        }
        return ok;
    }
}