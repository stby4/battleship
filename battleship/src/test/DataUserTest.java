package test;

import battleship.objects.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit test for data.User
 * @author Hinrich Kaestner
 */
public class DataUserTest {

    private battleship.data.User file = null;
    String uid = UUID.randomUUID().toString();

    @Before
    public void setUp() throws Exception {
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
        assertEquals(this.uid, user.getUid());
    }

    @Test
    public void testGet2() throws Exception {
        User user = file.get("Tom", "password2");
        assertNull(user);
    }
}