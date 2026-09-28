package test;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class EarthTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EarthTest
{
    /**
     * Default constructor for test class EarthTest
     */
    public EarthTest()
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
    public void shouldAllTheGridBeEarth(){
        EcoSafari pradera = new EcoSafari();
        int size = pradera.getSize();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                Entity e = pradera.get(i, j);
                assertTrue(e instanceof Earth);
            }
        }
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