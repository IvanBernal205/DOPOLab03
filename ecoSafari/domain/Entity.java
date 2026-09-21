package domain;
import java.awt.Color;

//Include the documentation
public interface Entity{
   public static final int SQUARE = 2;
   public static final int ROUND = 1;
    
   public void tic();
  
   public default void tac(){
   }

   public default int shape(){
      return SQUARE;
   }
  
   public abstract Color getColor();
  
    public default boolean isOrganism(){
      return false;
    }

   public abstract EcoSafari getHabitat();
   
   public default void lookAround (EcoSafari habitat, int r, int c, Entity e){
       
       for (int i = -1; i <= 1; i++){
            for (int j =-1; j <= 1; j++){
                if (i == 0 && j == 0){
                    continue;
                }
                
                if (habitat.get(r + i, c + j) != null){
                    //quedaron acá :D
                }
            }
       }
   }
    
   public default boolean disappear(){
     boolean ok=false;
     int [] position=this.getHabitat().find(this);
        if (position!=null){
            getHabitat().set(null,position[0],position[1]);
            ok=true;
        }
        return ok;
    }
    
    public default  boolean move(int deltaRows, int deltaColumns){
        int [] position=getHabitat().find(this);
        EcoSafari habitat=getHabitat();
        boolean ok=false;
        if (position!=null){
            int r = position[0];
            int c = position[1];
            if (habitat.isInside(r+deltaRows,c+deltaColumns)){
                habitat.set(null,r,c);
                habitat.set(this,r+deltaRows,c+deltaColumns);
                ok=true;
            }
        }
        return ok;
    }
}
