package test;

import domain.*;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ZebraTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ZebraTest
{
    /**
     * Default constructor for test class ZebraTest
     */
    public ZebraTest(){
        
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp(){
        
    }
    
    @Test
    public void shouldMoveToAValidPosition(){
        EcoSafari safari = new EcoSafari();

        Earth earth =  new Earth(safari, 17, 17);
        Zebra z = new Zebra(safari, 15, 15);
        
        int[] initialPos = safari.find(z);
        assertArrayEquals(new int[]{15,15}, initialPos);
        
        boolean moved = z.move(2,2);
        
        assertTrue(moved);
        
        int[] newPos = safari.find(z);
        assertArrayEquals(new int[]{17,17}, newPos);
    }
    
    @Test
    public void shouldChangeTheEnergyAfterMoving(){
        EcoSafari safari = new EcoSafari();

        for (int r = 13; r <= 17; r++){
            for (int c = 13; c <= 17; c++){
                new Earth(safari, r, c);
            }
        }

        Zebra z = new Zebra(safari, 15, 15);
        
        float initialEnergy = z.getEnergy();
        
        z.tic();
        z.tac();
        
        assertTrue(z.getEnergy() < initialEnergy);
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