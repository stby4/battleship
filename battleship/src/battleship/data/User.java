package battleship.data;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * User
 *
 * @author Hinrich Kaestner
 * @link http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
 * @link http://stackoverflow.com/questions/12977290/write-and-read-multiple-byte-in-file-with-java
 */
public class User extends BinaryFile {

    private static final String FILENAME = "user";

    public void store(battleship.objects.User user) throws IOException {
        List<battleship.objects.User> userList = null;
        try {
            userList = readAll();
            for(battleship.objects.User userHelper : userList) {
                if(user.getUid().equals(userHelper.getUid())) {
                    userList.remove(userHelper);
                    //break; // let list run through all saved game to make it a bit more failure resistant
                }
            }
        } catch (Exception e) {
            userList = new ArrayList<battleship.objects.User>();
        } finally {
            assert userList != null;
            userList.add(user);
        }

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(byteOut);
            out.writeObject(userList);
            byte[] data = byteOut.toByteArray();

            this.write(FILENAME, data);
            out.close();
        } finally {
            byteOut.close();
        }
    }

    private List<battleship.objects.User> readAll() {
        ByteArrayInputStream byteIn;
        List<battleship.objects.User> userList = null;
        try {
            byteIn = new ByteArrayInputStream(this.read(FILENAME));
            ObjectInput in = new ObjectInputStream(byteIn);
            //noinspection unchecked
            userList = (List<battleship.objects.User>) in.readObject();
            byteIn.close();
        } catch (Exception ignore) {
        }
        return userList;
    }

    public battleship.objects.User get(String username, String password) {
        List<battleship.objects.User> userList;
        try {
            userList = readAll();
            for (battleship.objects.User user : userList) {
                if (username.equals(user.getName()) && password.equals(user.getPassword())) {
                    return user;
                }
            }
        } catch (Exception e) {
            // do nothing, so null will be returned
        }
        return null;
    }

    public boolean checkUsernameExists(String username) {
        List<battleship.objects.User> userList;
        try {
            userList = readAll();
            for (battleship.objects.User user : userList) {
                if (username.equals(user.getName())) {
                    return true;
                }
            }
        } catch (Exception e) {
            // do nothing, so false will be returned
        }
        return false;
    }
}
