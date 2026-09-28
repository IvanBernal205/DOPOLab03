package test;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class LionTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LionTest
{
    private EcoSafari safari;

    /**
     * Default constructor for test class LionTest
     */
    public LionTest()
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
    public void shouldEatANeighborZebraAndGainFiftyPercent(){
        Lion simba = new Lion(safari, 0, 0);
        new Zebra(safari, 0, 1);
        new Zebra(safari, 1, 0);
        new Zebra(safari, 1, 1);
        simba.changeEnergy(-60);
        simba.tic();
        assertSame(simba, safari.get(0, 0));
        assertEquals(60, simba.getEnergy());
        int zebras = 0, earths = 0;
        for (Entity e : new Entity[]{safari.get(0,1), safari.get(1,0), safari.get(1,1)}){
            if (e instanceof Zebra) zebras++;
            if (e instanceof Earth) earths++;
        }
        assertEquals(2, zebras);
        assertEquals(1, earths);
    }

    @Test
    public void shouldDieWhenRunningOutOfEnergyAndLeaveEarth(){
        Lion scar = new Lion(safari, 24, 0);
        scar.changeEnergy(-99);
        scar.tic();
        assertEquals(0, scar.getEnergy());
        assertNull(safari.find(scar));
        for (int r = 23; r <= 24; r++){
            for (int c = 0; c <= 1; c++){
                assertTrue(safari.get(r, c) instanceof Earth);
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
        safari = null;
    }
}
