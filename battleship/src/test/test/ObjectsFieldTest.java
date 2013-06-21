package test;

import battleship.objects.Field;
import battleship.objects.Ship;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for objects.Field
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class ObjectsFieldTest {
    Field field = new Field(5, 7);

    /**
     * Place a shot.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        field.shoot(2, 3);
    }

    /**
     * Check field size.
     *
     * @throws Exception
     */
    @Test
    public void testGetSize() throws Exception {
        assertEquals(5, field.getSizeX());
        assertEquals(7, field.getSizeY());
    }

    /**
     * Check field status.
     *
     * @throws Exception
     */
    @Test
    public void testGetFieldStatus() throws Exception {
        assertEquals(Field.Fieldelements.WATER, field.getFieldStatus(4, 4));
        assertEquals(Field.Fieldelements.SHOT, field.getFieldStatus(2, 3));
    }

    /**
     * Add ship.
     *
     * @throws Exception
     */
    @Test
    public void testAddShip() throws Exception {
        field.addShip(new Ship(2, "Cutter", "cutter.jpg"));
        List<Ship> ships = field.getShips();
        assertEquals(1, ships.size());
        Ship cutter = ships.get(0);
        assertEquals("Cutter", cutter.getName());
        assertEquals("cutter.jpg", cutter.getImage());
        assertEquals(2, cutter.getLength());
    }
}
