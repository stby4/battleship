package battleship.logic;

import battleship.objects.Field;
import battleship.objects.User;

/**
 * Gameplay Battleship
 * @author H. Kaestner, T. Ohme
 */
public class Gameplay {
    private User user;
    private Game game;
    private Game.Playerelements currentPlayer = Game.Playerelements.ERROR;

    public Gameplay(User user) {
        this.user = user;

        // determine the player who will NOT do the first move
        if(user.getDefeats() > user.getVictories()) {
            currentPlayer = Game.Playerelements.COMPUTER;
        } else {
            currentPlayer = Game.Playerelements.USER;
        }
    }

    public Game.Playerelements startGame() {
        game = new Game();
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

    public void shootout() {
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
                    // TODO let user shoot: GUI connection
                } while(Field.Fieldelements.SHOT != game.placeShotUser(1, 1));
                currentPlayer = Game.Playerelements.COMPUTER;
                break;
        }
    }
}