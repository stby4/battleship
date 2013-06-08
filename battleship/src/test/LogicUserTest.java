package test;

import battleship.objects.DuplicateUsersException;
import battleship.objects.IncompleteDataException;
import battleship.objects.NotMatchingPasswordsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Unit test for logic.User
 *
 * @author Hinrich Kaestner
 */
public class LogicUserTest {

    private battleship.logic.User lUser = battleship.logic.User.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateNewUser() throws Exception {
        String uid = lUser.createNewUser("Horst", "password2", "password2");
        assertNotNull(uid);

    }

    @Test
    public void testDuplicateUsersException() {
        boolean thrown = false;
        try {
        lUser.createNewUser("Chantal", "password3", "password3");
        lUser.createNewUser("Chantal", "password4", "password4");
        } catch(DuplicateUsersException due) {
            thrown = true;
        } catch(Exception e) {

        }
        assertTrue(thrown);
    }

    @Test
    public void testNotMatchingPasswordsException() {
        boolean thrown = false;
        try {
            lUser.createNewUser("Jacqueline", "password5", "pasword5");
        } catch(NotMatchingPasswordsException nmpe) {
            thrown = true;
        } catch (Exception e) {

        }
        assertTrue(thrown);
    }

    @Test
    public void testIncompleteDataException() {
        boolean thrown = false;
        try {
            lUser.createNewUser("Urs", "password6", "");
        } catch(IncompleteDataException ide) {
            thrown = true;
        } catch (Exception e) {

        }
        assertTrue(thrown);
    }

    @Test
    public void testLogin() throws Exception {
        lUser.createNewUser("Angela", "password7", "password7");
        lUser.createNewUser("Peer", "password8", "password8");
        lUser.login("Angela", "password7");
        battleship.objects.User user = lUser.getUser();
        assertEquals("Angela", user.getName());
        assertEquals("password7", user.getPassword());
    }

    @Test
    public void testGetUser() throws Exception {
        lUser.createNewUser("Arnold", "password1", "password1");
        battleship.objects.User user = lUser.getUser();
        assertEquals("Arnold", user.getName());
        assertEquals("password1", user.getPassword());
        assertNotNull(user.getUid());
    }
}
