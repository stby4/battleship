package test;

import battleship.logic.Game;
import battleship.objects.Field;
import battleship.objects.Ship;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for logic.Game
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class LogicGameTest {
    Game game = new Game();

    /**
     * Place a ship.
     *
     * @throws Exception
     */
    @Test
    public void testPlaceShipUser() throws Exception {
        Ship ship = game.getNextUnsetShip();
        ship.setPosition(1, 2, Field.Directionelements.VERTICAL);
        assertTrue(game.placeShipUser(ship));
        Field fieldUser = game.getField(Game.Playerelements.USER);
        assertEquals(Field.Fieldelements.SHIP, fieldUser.getFieldStatus(1,2));
    }

    /**
     * Place a shot.
     *
     * @throws Exception
     */
    @Test
    public void testPlaceShotUser() throws Exception {
        assertNotNull(game.placeShotUser(3, 4));
    }

    /**
     * Let the computer shoot.
     *
     * @throws Exception
     */
    @Test
    public void testGetLastShotComputer() throws Exception {
        game.placeShotComputer();
        assertNotNull(game.getLastShotComputerX());
        assertNotNull(game.getLastShotComputerY());

    }
}
