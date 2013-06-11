package battleship.logic;

import java.io.IOException;

/**
 * logic.Instance
 * @author H. Kaestner
 */
public class Instance {
    private static Instance instance;
    private Gameplay gameplay;

    private Instance() {}

    public static Instance getInstance() {
        if(null == instance) {
            instance = new Instance();
        }
        return instance;
    }

    public void newGame(battleship.objects.User user) {
        gameplay = new Gameplay(user);
    }

    public boolean loadGame(String gid) {
        gameplay = new battleship.data.Game().get(gid);
        if(null == gameplay) {
            return false;
        }
        return true;
    }

    public void storeGame() throws IOException {
        new battleship.data.Game().store(gameplay);
    }

    public Gameplay getGameplay() {
        return gameplay;
    }
}
