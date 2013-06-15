package test;

import battleship.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Unit test for data.User
 *
 * @author Hinrich Kaestner
 */
public class DataUserTest {

    private battleship.data.User file = null;

    @Before
    public void setUp() throws Exception {
        File store = new File("game.bin");
        store.delete();
        String uid = UUID.randomUUID().toString();
        User user = new User(uid, "Hinrich", "password1");
        file = new battleship.data.User();

        file.store(user);
    }

    @Test
    public void testGet1() throws Exception {
        User user = file.get("Hinrich", "password1");
        assertEquals("Hinrich", user.getName());
        assertEquals("password1", user.getPassword());
    }

    @Test
    public void testGet2() throws Exception {
        User user = file.get("Tom", "password2");
        assertNull(user);
    }

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