package battleship.data;

import battleship.logic.Gameplay;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores and reads saved games (saved as Gameplay objects).
 *
 * @author Hinrich Kaestner, Tom Ohme
 * @link http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
 */
public class GameFile extends BinaryFile {

    private static final String FILENAME = "game";

    /**
     * Saves a gameplay.
     *
     * @param gameplay Gameplay object that includes the latest game
     * @throws IOException
     */
    public void store(Gameplay gameplay) throws IOException {
        List<Gameplay> gameplayList = null;
        try {
            gameplayList = readAll();
            for (Gameplay gameplayHelper : gameplayList) {
                if (gameplay.getUser().getUid().equals(gameplayHelper.getUser().getUid())) {
                    gameplayList.remove(gameplay);
                    //break; // let list run through all saved game to make it a bit more failure resistant
                }
            }
        } catch (Exception e) {
            gameplayList = new ArrayList<Gameplay>();
        } finally {
            assert gameplayList != null;
            gameplayList.add(gameplay);
        }

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(byteOut);
            out.writeObject(gameplayList);
            byte[] data = byteOut.toByteArray();

            this.write(FILENAME, data);
            out.close();
        } finally {
            byteOut.close();
        }
    }

    /**
     * Reads all saved Gameplay objects and returns them in a list.
     *
     * @return list of all saved gameplays
     */
    private List<Gameplay> readAll() {
        ByteArrayInputStream byteIn;
        List<Gameplay> gameplayList = null;
        try {
            byteIn = new ByteArrayInputStream(this.read(FILENAME));
            ObjectInput in = new ObjectInputStream(byteIn);
            //noinspection unchecked
            gameplayList = (List<Gameplay>) in.readObject();
            byteIn.close();
        } catch (Exception ignore) {
        }
        return gameplayList;
    }

    /**
     * Returns a specific Gameplay, referenced by the gameplay ID gid.
     *
     * @param gid gameplay ID
     * @return Gameplay with the matching gid, or null
     */
    public Gameplay get(String gid) {
        List<Gameplay> gameplayList;
        try {
            gameplayList = readAll();
            for (Gameplay gameplay : gameplayList) {
                if (gid.equals(gameplay.getGid())) {
                    return gameplay;
                }
            }
        } catch (Exception e) {
            // do nothing, so null will be returned
        }
        return null;
    }
}
