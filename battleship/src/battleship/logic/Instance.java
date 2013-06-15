package battleship.logic;

import java.io.IOException;

/**
 * Instance is used to load and store a gameplay. A new game should always be created through this class.
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class Instance {
    private static Instance instance;
    private Gameplay gameplay;

    private Instance() {}

    /**
     * This class is a singleton.
     *
     * @return An instance of Instance.
     */
    public static Instance getInstance() {
        if(null == instance) {
            instance = new Instance();
        }
        return instance;
    }

    /**
     * Sets up all required objects for a new game.
     *
     * @param user The user who wants to play.
     */
    public void newGame(battleship.objects.User user) {
        gameplay = new Gameplay(user);
    }

    /**
     * Loads a previously saved game.
     *
     * @param gid the game ID
     * @return the loaded gameplay, or null if no matching gameplay was found
     */
    public boolean loadGame(String gid) {
        gameplay = new battleship.data.Game().get(gid);
        return null != gameplay;
    }

    /**
     * Stores the current game.
     *
     * @throws IOException
     */
    public void storeGame() throws IOException {
        new battleship.data.Game().store(gameplay);
    }

    /**
     * Returns the current Gameplay
     *
     * @return the current Gameplay
     */
    public Gameplay getGameplay() {
        return gameplay;
    }
}
