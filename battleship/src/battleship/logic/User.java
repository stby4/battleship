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
    private static User user = null;
    private battleship.objects.User oUser;

    private User() {

    }

    public static User getInstance() {
        if(null == user) {
            user = new User();
        }
        return user;
    }

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
     * @param username
     * @param password
     * @return
     * @throws WrongCredentialsException
     * @throws IncompleteDataException
     */
    public String login(String username, String password) throws WrongCredentialsException, IncompleteDataException {
        if (username.equals("") || password.equals("")) {
            throw new IncompleteDataException("Please fill out all fields.");
        }
        battleship.data.User dUser = new battleship.data.User();
        oUser = dUser.get(username, password);
        if(null == oUser) {
            throw new WrongCredentialsException("An user with the given credentials does not exist. Please register if you have not already done so.");
        }
        return oUser.getUid();
    }

    /**
     * @return
     */
    public battleship.objects.User getUser() {
        return oUser;
    }
}
