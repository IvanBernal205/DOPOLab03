package domain;
import java.awt.Color;


/**
 * Write a description of class Grass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grass implements Entity
{
    private EcoSafari habitat;
    
    public Grass(EcoSafari habitat, int row, int column){
        this.habitat = habitat;
        habitat.set((Entity)this, row, column);
    }
    
    public void tic(){
    
    }
    
    public Color getColor(){
        return new Color(86, 150, 50); // verde pasto
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }
}