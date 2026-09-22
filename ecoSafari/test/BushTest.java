package test;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BushTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BushTest
{
    /**
     * Default constructor for test class BushTest
     */
    public BushTest()
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
    public void shouldDisappearWhenNextToAnElephant(){
        EcoSafari safari = new EcoSafari();       
        Elephant dumbo = new Elephant(safari, 5, 5);
        Bush mopane = new Bush(safari, 4, 5);           
        mopane.tic();
        mopane.tac();
        assertNull(safari.get(4, 5));
    }

    @Test
    public void shouldStayWhenNoElephantIsNear(){
        EcoSafari safari = new EcoSafari();
        Bush acacia = new Bush(safari, 0, 0);       
        acacia.tic();
        assertSame(acacia, safari.get(0, 0));
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