package domain;
import java.awt.Color;


/**
 * A Stella which is around the storm.
 *
 * @author Ivan Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 12-11-2026
 */

public class Stella implements Entity{
    private EcoSafari habitat;

    public Stella(EcoSafari habitat, int row, int column){
        this.habitat = habitat;
        habitat.set((Entity) this, row, column);
    }

    public void tic(){
        disappear();
    }

    @Override
    public void tac(){
        //
    }

    @Override
    public Color getColor(){
        return Color.gray;
    }

    @Override
    public EcoSafari getHabitat(){
        return habitat;
    }
}