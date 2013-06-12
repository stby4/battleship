package battleship.data;

import battleship.logic.Gameplay;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author H. Kaestner
 * @link http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
 */
public class Game extends BinaryFile {

    private static final String FILENAME = "game";

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
