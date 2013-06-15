package battleship.logic;

import battleship.objects.DuplicateUsersException;
import battleship.objects.IncompleteDataException;
import battleship.objects.NotMatchingPasswordsException;
import battleship.objects.WrongCredentialsException;

import java.io.IOException;
import java.util.UUID;


/**
 * Create new useers, login existing users and request the currently logged in user.
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class User {
    private static User user = null;
    private battleship.objects.User oUser;

    private User() {

    }

    /**
     * This class is a singleton.
     *
     * @return an instance of User
     */
    public static User getInstance() {
        if(null == user) {
            user = new User();
        }
        return user;
    }

    /**
     * Checks if the entered data is valid and creates a new user.
     *
     * @param username user name
     * @param password1 password, first entry
     * @param password2 password, second entry
     * @return user ID
     * @throws DuplicateUsersException
     * @throws NotMatchingPasswordsException
     * @throws IncompleteDataException
     * @throws IOException
     */
    public String createNewUser(String username, String password1, String password2) throws DuplicateUsersException, NotMatchingPasswordsException, IncompleteDataException, IOException {
        if (username.equals("") || password1.equals("") || password2.equals("")) {
            throw new IncompleteDataException("Please fill out all fields.");
        }
        battleship.data.User dUser = new battleship.data.User();
        if (dUser.checkUsernameExists(username)) {
            throw new DuplicateUsersException("This username has already been taken. Please choose another one.");
        }
        if (!password1.equals(password2)) {
            throw new NotMatchingPasswordsException("The passwords do not match. Please try again.");
        }

        String uid = UUID.randomUUID().toString();
        oUser = new battleship.objects.User(uid, username, password2);
        dUser.store(oUser);
        return uid;
    }

    /**
     * Checks if the entered data is valid and logs the user in.
     *
     * @param username user name
     * @param password password
     * @throws WrongCredentialsException
     * @throws IncompleteDataException
     */
    public void login(String username, String password) throws WrongCredentialsException, IncompleteDataException {
        if (username.equals("") || password.equals("")) {
            throw new IncompleteDataException("Please fill out all fields.");
        }
        battleship.data.User dUser = new battleship.data.User();
        oUser = dUser.get(username, password);
        if(null == oUser) {
            throw new WrongCredentialsException("An user with the given credentials does not exist. Please register if you have not already done so.");
        }
    }

    /**
     * Returns the current user.
     *
     * @return current user
     */
    public battleship.objects.User getUser() {
        return oUser;
    }
}
