package battleship.logic;

import battleship.objects.*;

/**
 * logic.Instance
 * @author H. Kaestner
 */
public class Instance {
    private static Instance instance = null;
    private Gameplay gameplay;

    private Instance() {}

    public static Instance getInstance() {
        if(null == instance) {
            instance = new Instance();
        }
        return instance;
    }

    public void newGame(battleship.objects.User user) {
        this.gameplay = new Gameplay(user);
    }

    public void loadGame(int gid) {
        // TODO load game from DAO
    }

    public void storeGame() {
        // TODO sent gameplay to DAO
    }

    public Gameplay getGameplay() {
        return gameplay;
    }
}
