import battleship.logic.UserManagement;
import battleship.objects.DuplicateUsersException;
import battleship.objects.IncompleteDataException;
import battleship.objects.NotMatchingPasswordsException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
/**
 * Unit test for logic.UserManagement
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class LogicUserTest {

    private UserManagement lUserManagement = UserManagement.getInstance();

    /**
     * Delete existing file.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        File store = new File("user.bin");
        store.delete();
    }

    /**
     * Create a new user.
     *
     * @throws Exception
     */
    @Test
    public void testCreateNewUser() throws Exception {
        String uid = lUserManagement.createNewUser("Horst", "password2", "password2");
        assertNotNull(uid);

    }

    /**
     * Create duplicates.
     */
    @Test
    public void testDuplicateUsersException() {
        boolean thrown = false;
        try {
        lUserManagement.createNewUser("Chantal", "password3", "password3");
        lUserManagement.createNewUser("Chantal", "password4", "password4");
        } catch(DuplicateUsersException due) {
            thrown = true;
        } catch(Exception e) {

        }
        assertTrue(thrown);
    }

    /**
     * Create user with different passwords.
     */
    @Test
    public void testNotMatchingPasswordsException() {
        boolean thrown = false;
        try {
            lUserManagement.createNewUser("Jacqueline", "password5", "pasword5");
        } catch(NotMatchingPasswordsException nmpe) {
            thrown = true;
        } catch (Exception e) {

        }
        assertTrue(thrown);
    }

    /**
     * Test user with missing second password.
     */
    @Test
    public void testIncompleteDataException() {
        boolean thrown = false;
        try {
            lUserManagement.createNewUser("Urs", "password6", "");
        } catch(IncompleteDataException ide) {
            thrown = true;
        } catch (Exception e) {

        }
        assertTrue(thrown);
    }

    /**
     * Test login with multiple users.
     *
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception {
        lUserManagement.createNewUser("Angela", "password7", "password7");
        lUserManagement.createNewUser("Peer", "password8", "password8");
        lUserManagement.login("Angela", "password7");
        battleship.objects.User user = lUserManagement.getUser();
        assertEquals("Angela", user.getName());
        assertEquals("password7", user.getPassword());
    }

    /**
     * Test receiving of data.
     *
     * @throws Exception
     */
    @Test
    public void testGetUser() throws Exception {
        lUserManagement.createNewUser("Arnold", "password1", "password1");
        battleship.objects.User user = lUserManagement.getUser();
        assertEquals("Arnold", user.getName());
        assertEquals("password1", user.getPassword());
        assertNotNull(user.getUid());
    }
}
