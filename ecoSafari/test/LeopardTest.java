package test;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class LeopardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LeopardTest
{
    /**
     * Default constructor for test class LeopardTest
     */
    public LeopardTest()
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
    }
    
    @Test 
    public void shouldCreateALeopardInTheSafariEcoSystem(){
        EcoSafari safari = new EcoSafari();
        Leopard leo = new Leopard(safari, 11, 11);
        
        assertEquals(safari.get(11, 11), leo);
    }
    
    @Test
    public void shouldDestroyABushWhenItIsClose(){
        EcoSafari safari = new EcoSafari();
        Bush mopane = new Bush(safari, 10,10);
        Leopard leo = new Leopard(safari, 11, 11);
        
        leo.tic();
        leo.tac();
        
        assertNull(safari.get(10, 10));
    }
    
    @Test
    public void shouldMoveTwoStepsInJustATicTac(){
        EcoSafari safari = new EcoSafari();
        Leopard leo = new Leopard (safari, 10, 10);
        
        leo.tic();
        leo.tac();
        
        assertEquals(safari.get(8, 8), leo);
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