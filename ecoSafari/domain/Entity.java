package domain;
import java.awt.Color;

//Include the documentation
public interface Entity{
    public static final int SQUARE = 2;
    public static final int ROUND = 1;
    
    public void tic();

    /**
     * Performs the tac action.
     */
    public default void tac(){
    }

    /**
     * Returns the shape of the entity.
     * @return the shape of the entity
     */
    public default int shape(){
        return SQUARE;
    }

    public abstract Color getColor();

    /**
     * Returns whether the entity is an organism or not.
     * @return true if the entity is an organism, false otherwise
     */
    public default boolean isOrganism(){
        return false;
    }

    public abstract EcoSafari getHabitat();

    /**
     * Returns the neighbors of the entity.
     * @return an array of entities
     */
    public default Entity[] neighbors(){
        int[] position = getHabitat().find(this);
        Entity[] result = new Entity[8];
        if (position == null) return result;
        int k = 0;
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0) continue;
                result[k++] = getHabitat().get(position[0] + i, position[1] + j);
            }
        }
        return result;
    }
    
    /**
     * Removes the entity from its habitat.
     * @return true if the entity was successfully removed, false otherwise
     */
    public default boolean disappear(){
        boolean ok=false;
        int [] position=this.getHabitat().find(this);
        if (position!=null){
            getHabitat().set(null,position[0],position[1]);
            ok=true;
        }
        return ok;
    }
    
    /**
     * Moves the entity.
     * @param deltaRows the change in row position
     * @param deltaColumns the change in column position
     * @return true if the entity was successfully moved, false otherwise
     */
    public default  boolean move(int deltaRows, int deltaColumns){
        int [] position=getHabitat().find(this);
        EcoSafari habitat=getHabitat();
        boolean ok=false;
        if (position!=null){
            int r = position[0];
            int c = position[1];
            if (habitat.isInside(r+deltaRows,c+deltaColumns) && habitat.get(r+deltaRows,c+deltaColumns)==null){
                habitat.set(null,r,c);
                habitat.set(this,r+deltaRows,c+deltaColumns);
                ok=true;
            }
        }
        return ok;
    }
}
