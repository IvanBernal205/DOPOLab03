package test;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class StormTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StormTest
{
    private EcoSafari safari;
 
    /**
     * Default constructor for test class StormTest
     */
    public StormTest()
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
    public void shouldMoveDiagonallyNorthEast(){
        Storm thor = new Storm(safari, 5, 5);
        safari.ticTac();
        assertSame(thor, safari.get(4, 6));
        assertNotSame(thor, safari.get(5, 5));
    }
 
    @Test
    public void shouldDestroyWhatIsInItsCenter(){
        Bush mopane = new Bush(safari, 4, 6);
        Storm thor = new Storm(safari, 5, 5);
        safari.ticTac();
        assertSame(thor, safari.get(4, 6));
        assertNull(safari.find(mopane));
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