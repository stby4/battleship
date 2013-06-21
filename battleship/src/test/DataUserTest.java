import battleship.data.UserFile;
import battleship.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Unit test for data.UserManagement
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class DataUserTest {

    private UserFile file = null;

    /**
     * Delete existing file and create a first user.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        File store = new File("game.bin");
        store.delete();
        String uid = UUID.randomUUID().toString();
        User user = new User(uid, "Hinrich", "password1");
        file = new UserFile();

        file.store(user);
    }

    /**
     * Check if first user was saved.
     *
     * @throws Exception
     */
    @Test
    public void testGet1() throws Exception {
        User user = file.get("Hinrich", "password1");
        assertEquals("Hinrich", user.getName());
        assertEquals("password1", user.getPassword());
    }

    /**
     * Check if not existing users are handled.
     *
     * @throws Exception
     */
    @Test
    public void testGet2() throws Exception {
        User user = file.get("Tom", "password2");
        assertNull(user);
    }

    /**
     * Check multiple users.
     *
     * @throws Exception
     */
    @Test
    public void testCheckUsernameExists() throws Exception {
        User user = new User(UUID.randomUUID().toString(), "Sabine", "password3");
        file.store(user);
        user = new User(UUID.randomUUID().toString(), "Peter", "password4");
        file.store(user);

        assertTrue(file.checkUsernameExists("Sabine"));
        assertTrue(file.checkUsernameExists("Peter"));
        assertTrue(file.checkUsernameExists("Hinrich"));
        assertFalse(file.checkUsernameExists("Tom"));
    }

    /**
     * Update existing user.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateUserInfo() throws Exception {
        User user = new User(UUID.randomUUID().toString(), "Gerd", "password5");
        file.store(user);
        User user1 = file.get("Gerd", "password5");
        assertNull(user1.getLastGame());
        String gid = UUID.randomUUID().toString();
        user1.setLastGame(gid);
        file.store(user1);
        User user2 = file.get("Gerd", "password5");
        assertEquals(gid, user2.getLastGame());
    }
}