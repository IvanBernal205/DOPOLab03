package test;

import domain.*;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ElephantTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ElephantTest
{
    private EcoSafari safari;
 
    /**
     * Default constructor for test class ElephantTest
     */
    public ElephantTest()
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
    public void shouldMoveDiagonallySouthEast(){
        Elephant dumbo = new Elephant(safari, 5, 5);
        dumbo.tic();
        dumbo.tac();
        assertSame(dumbo, safari.get(6, 6));
        assertNull(safari.get(5, 5));
    }
 
    @Test
    public void shouldLoseTenPercentEnergyPerMove(){
        Elephant babar = new Elephant(safari, 10, 10);
        assertEquals(100, babar.getEnergy());
        babar.tic();
        babar.tac();
        assertEquals(90, babar.getEnergy());
        babar.tic();
        babar.tac();
        assertEquals(80, babar.getEnergy());
    }
 
    @Test
    public void shouldChangeColorWhenTired(){
        Elephant dumbo = new Elephant(safari, 5, 5);
        assertEquals(Color.DARK_GRAY, dumbo.getColor());
        for (int i = 0; i < 3; i++){
            dumbo.tic();
            dumbo.tac();
        }
        assertEquals(70, dumbo.getEnergy());
        assertEquals(Color.LIGHT_GRAY, dumbo.getColor());
    }
    
    @Test
    public void shouldLoseTenEnergyPointsPerMove(){
        Elephant dumbo = new Elephant(safari, 5, 5);
        safari.ticTac();
        assertSame(dumbo, safari.get(6, 6));
        assertNull(safari.get(5, 5));
        assertNull(safari.get(7, 7));   // no avanzo dos veces
        assertEquals(90, dumbo.getEnergy());
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