package test;

import battleship.objects.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Unit test for data.User
 *
 * @author Hinrich Kaestner
 */
public class DataUserTest {

    private battleship.data.User file = null;
    String uid = null;

    @Before
    public void setUp() throws Exception {
        this.uid = UUID.randomUUID().toString();
        User user = new User(this.uid, "Hinrich", "password1");
        file = new battleship.data.User();

        file.store(user);
    }

    @After
    public void tearDown() throws Exception {

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
}