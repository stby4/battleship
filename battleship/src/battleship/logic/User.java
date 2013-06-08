package battleship.logic;

import battleship.objects.DuplicateUsersException;
import battleship.objects.IncompleteDataException;
import battleship.objects.NotMatchingPasswordsException;
import battleship.objects.WrongCredentialsException;

import java.io.IOException;
import java.util.UUID;

/**
 * logic.User
 *
 * @author H. Kaestner
 */
public class User {
    private battleship.objects.User user = null; // TODO create user object

    /**
     *
     * @param username
     * @param password1
     * @param password2
     * @return
     * @throws DuplicateUsersException
     * @throws NotMatchingPasswordsException
     * @throws IncompleteDataException
     * @throws IOException
     */
    public String createNewUser(String username, String password1, String password2) throws DuplicateUsersException, NotMatchingPasswordsException, IncompleteDataException, IOException {
        battleship.data.User dUser = new battleship.data.User();
        if (username.equals("") || password1.equals("") || password2.equals("")) {
            throw new IncompleteDataException("Please fill out all fields.");
        }
        if (dUser.checkUsernameExists(username)) {
            throw new DuplicateUsersException("This username has already been taken. Please choose another one.");
        }
        if (password1.equals(password2)) {
            throw new NotMatchingPasswordsException("The passwords do not match. Please try again.");
        }

        String uid = UUID.randomUUID().toString();
        user = new battleship.objects.User(uid, username, password2);
        dUser.store(user);
        return uid;
    }

    /**
     * @param username
     * @param password
     * @return
     * @throws WrongCredentialsException
     * @throws IncompleteDataException
     */
    public int login(String username, String password) throws WrongCredentialsException, IncompleteDataException {
        // TODO everyhting
        // throw new DuplicateUsersException("Wir konnten keinen Benutzer mit den angegebenen Daten finden.");
        return 1;
    }

    /**
     * @return
     */
    public battleship.objects.User getUser() {
        return user;
    }
}
