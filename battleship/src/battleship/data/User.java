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
public class User extends binaryFile {

    private int uid = -1;

    public void store(battleship.objects.User user) throws IOException {
        List<battleship.objects.User> users = null;
        try {
            users = readAll();
        } catch (Exception e) {
            users = new ArrayList<battleship.objects.User>();
        } finally {
            users.add(user);
        }

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(byteOut);
            out.writeObject(users);
            byte[] data = byteOut.toByteArray();

            this.write("user", data);
        } finally {
            out.close();
            byteOut.close();
        }
    }

    private List<battleship.objects.User> readAll() throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteIn = null;
        List<battleship.objects.User> users = null;
        try {
            byteIn = new ByteArrayInputStream(this.read("user"));
            ObjectInput in = new ObjectInputStream(byteIn);
            users = (List<battleship.objects.User>) in.readObject();
        } finally {
            byteIn.close();
        }
        return users;
    }

    public battleship.objects.User get(String username, String password) {
        List<battleship.objects.User> users = null;
        try {
            users = readAll();
            for (battleship.objects.User user : users) {
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
        List<battleship.objects.User> users = null;
        try {
            users = readAll();
            for (battleship.objects.User user : users) {
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
