import battleship.logic.Game;
import battleship.logic.Gameplay;
import battleship.objects.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for logic.Gameplay
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class LogicGameplayTest {
    Gameplay gp = new Gameplay(new User("gshdbbldsgh", "Ulrike", "1234"));

    @Test
    public void testShootComputer() throws Exception {
        assertNotNull(gp.shoot());
    }

    @Test
    public void testShootUser() throws Exception {
        assertNotNull(gp.shoot(6,7));
    }

    @Test
    public void testGetGame() throws Exception {
        assertTrue(gp.getGame() instanceof Game);
    }

    @Test
    public void testGetGid() throws Exception {
        assertNotNull(gp.getGid());
    }
}
