package battleship.logic;

import battleship.objects.DuplicateUsersException;
import battleship.objects.NotMatchingPasswordsException;
import battleship.objects.WrongCredentialsException;

/**
 * logic.User
 * @author H. Kaestner
 */
public class User {
    private battleship.objects.User user = null;

    /**
     *
     * @param name
     * @param password1
     * @param password2
     * @return uid
     */
    public int createNewUser(String name, String password1, String password2) throws DuplicateUsersException, NotMatchingPasswordsException{
        /* TODO check if username exists
        if(username exists) {
            throw new DuplicateUsersException("Ein Benutzer mit diesem Namen existiert schon.");
        }
         */
        if(password1.equals(password2)) {
            throw new NotMatchingPasswordsException("Die Passwörter stimmen nicht miteinander überein.");
        }
        // TODO create new entry in DAO

        return 1;
    }

    /**
     *
     * @param name
     * @param password
     * @return uid
     * @throws Exception
     */
    public int login(String name, String password) throws WrongCredentialsException {
        // TODO everyhting
        // throw new DuplicateUsersException("Wir konnten keinen Benutzer mit den angegebenen Daten finden.");
        return 1;
    }
}
