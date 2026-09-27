package test;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MamutTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MamutTest
{
    private EcoSafari safari;
    /**
     * Default constructor for test class MamutTest
     */
    public MamutTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        safari = new EcoSafari();
    }
    
    @Test
    public void shouldGetTiredSlowerThanANormalElephant(){
        Mamut manny = new Mamut(safari, 20, 5);
        Elephant dumbo = new Elephant(safari, 1, 20);
        for (int i = 0; i < 4; i++){
            manny.tic();  
            dumbo.tic();
            manny.tac();   
            dumbo.tac();
        }
        assertEquals(80, manny.getEnergy());
        assertEquals(60, dumbo.getEnergy());
        assertNotNull(safari.find(manny));
    }
    
    @Test
    public void shouldMoveAndLoseFiveEnergy(){
        Mamut manny = new Mamut(safari, 20, 20);
        manny.tic();
        manny.tac();
        int[] position = safari.find(manny);
        assertNotNull(position);
        assertNull(safari.get(20, 20));
        assertEquals(95, manny.getEnergy());
    }
    
    @Test
    public void shouldRecoverEnergyWhenSurroundedByBushes(){
        Mamut manny = new Mamut(safari, 20, 20);
        for (int r = 18; r <= 22; r++){
            for (int c = 18; c <= 22; c++){
                if (r != 20 || c != 20) new Bush(safari, r, c);
            }
        }
        manny.tic();
        manny.tac();
        assertEquals(100, manny.getEnergy()); // 100 - 5 + 20 (tope 100)
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}