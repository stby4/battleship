package battleship.logic;

import battleship.objects.Field;
import battleship.objects.User;

import java.util.UUID;

/**
 * starts a new game, has the logic of the games rules
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class Gameplay implements java.io.Serializable {
    private User user;
    private Game game;
    private Game.Playerelements currentPlayer = Game.Playerelements.ERROR;
    private String gid;

    /**
     * Creates a new Gameplay and subsequently a new Game
     *
     * @param user The user who wants to play.
     */
    public Gameplay(User user) {
        this.user = user;
        game = new Game();
        this.gid = UUID.randomUUID().toString();

        // determine the player who will NOT do the first move
        if(user.getDefeats() > user.getVictories()) {
            currentPlayer = Game.Playerelements.COMPUTER;
        } else {
            currentPlayer = Game.Playerelements.USER;
        }
    }

    // @deprecated
    public Game.Playerelements startGame() {
        Game.Playerelements winner = game.getWinner();
        do {
            shootout();
        } while(Game.Playerelements.ERROR == winner);
        if(Game.Playerelements.USER == winner) {
            user.addVictory();
        } else {
            user.addDefeat();
        }
        return winner;
    }

    // @deprecated
    void shootout() {
        switch (currentPlayer) {
            case COMPUTER:
                do {
                    // nothing
                } while(Field.Fieldelements.SHOT != game.placeShotComputer());
                currentPlayer = Game.Playerelements.USER;
                break;
            case USER:
                do {
                    // nothing, so far
                    // TODO let user shoot: GUI connection (see getCurrentPlayer())
                } while(Field.Fieldelements.SHOT != game.placeShotUser(1, 1));
                currentPlayer = Game.Playerelements.COMPUTER;
                break;
        }
    }

    /**
     * Returns the user of this game.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the Game class that is used in this gameplay.
     *
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Returns the game ID of this game.
     *
     * @return game ID
     */
    public String getGid() {
        return gid;
    }

    /**
     * Returns the current Player.
     *
     * @return current player
     */
    public Game.Playerelements getCurrentPlayer() {
        // TODO probably move some logic from shootout to this function, so the GUI can access it
        return currentPlayer;
    }
}
