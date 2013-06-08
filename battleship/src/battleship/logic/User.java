package battleship.logic;

import battleship.objects.DuplicateUsersException;
import battleship.objects.IncompleteDataException;
import battleship.objects.NotMatchingPasswordsException;
import battleship.objects.WrongCredentialsException;

/**
 * logic.User
 * @author H. Kaestner
 */
public class User {
    private battleship.objects.User user = null; // TODO create user object

    /**
     *
     * @param username
     * @param password1
     * @param password2
     * @return uid
     */
    public int createNewUser(String username, String password1, String password2) throws DuplicateUsersException, NotMatchingPasswordsException, IncompleteDataException{
        battleship.data.User dUser = new battleship.data.User();
        if(username exists) {
            throw new DuplicateUsersException("Ein Benutzer mit diesem Namen existiert schon.");
        }
         */
        if(password1.equals(password2)) {
            throw new NotMatchingPasswordsException("Die Passwörter stimmen nicht miteinander überein.");
        }
        // TODO throw exceotion for incomplete data
        // TODO create new entry in DAO...

        return 1;
    }

    /**
     *
     * @param username
     * @param password
     * @return uid
     * @throws Exception
     */
    public int login(String username, String password) throws WrongCredentialsException {
        // TODO everyhting
        // throw new DuplicateUsersException("Wir konnten keinen Benutzer mit den angegebenen Daten finden.");
        return 1;
    }

    /**
     *
     * @return
     */
    public battleship.objects.User getUser() {
        return user;
    }
}
