package test;

import battleship.objects.Field;
import battleship.objects.Ship;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for objects.Ship
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class ObjectsShipTest {
    Ship ship = new Ship(1, "Fisherboat", "fisherboat.png");

    /**
     * Set position for ship.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        ship.setPosition(3, 4, Field.Directionelements.HORIZONTAL);
    }

    /**
     * Change position and check if right values are returned.
     *
     * @throws Exception
     */
    @Test
    public void testSetPosition() throws Exception {
        ship.setPosition(1, 2, Field.Directionelements.VERTICAL);
        assertEquals(1, ship.getPosX());
        assertEquals(2, ship.getPosY());
        assertEquals(Field.Directionelements.VERTICAL, ship.getDirection());
        ship.setPosition(3, 4, Field.Directionelements.HORIZONTAL);
    }

    /**
     * Check if isSet boolean is true.
     *
     * @throws Exception
     */
    @Test
    public void testIsSet() throws Exception {
        assertTrue(ship.isSet());
    }

    /**
     * Unset ship.
     *
     * @throws Exception
     */
    @Test
    public void testUnset() throws Exception {
        ship.unset();
        assertFalse(ship.isSet());
        assertEquals(-1, ship.getPosX());
        assertEquals(-1, ship.getPosY());
        ship.setPosition(3, 4, Field.Directionelements.HORIZONTAL);
    }

    /**
     * Sink ship.
     *
     * @throws Exception
     */
    @Test
    public void testGetSunk() throws Exception {
        ship.shoot(3, 4);
        assertTrue(ship.getSunk());
    }

    /**
     * Get the ships name.
     *
     * @throws Exception
     */
    @Test
    public void testGetName() throws Exception {
        assertEquals("Fisherboat", ship.getName());
    }

    /**
     * Get the ships image.
     *
     * @throws Exception
     */
    @Test
    public void testGetImage() throws Exception {
        assertEquals("fisherboat.png", ship.getImage());
    }

    /**
     * Get ships position.
     *
     * @throws Exception
     */
    @Test
    public void testGetPos() throws Exception {
        assertEquals(3, ship.getPosX());
        assertEquals(4, ship.getPosY());
        assertEquals(Field.Directionelements.HORIZONTAL, ship.getDirection());
    }

    /**
     * Get ships length.
     * @throws Exception
     */
    @Test
    public void testGetLength() throws Exception {
        assertEquals(1, ship.getLength());
    }

    /**
     * Clone ship.
     *
     * @throws Exception
     */
    @Test
    public void testClone() throws Exception {
        Ship ship1 = ship.clone();
        ship1.setPosition(8, 9, Field.Directionelements.VERTICAL);
        assertEquals(3, ship.getPosX());
        assertEquals(4, ship.getPosY());
        assertEquals(8, ship1.getPosX());
        assertEquals(9, ship1.getPosY());
    }
}
