package battleship.objects;

import battleship.data.UserFile;

/**
 * UserManagement class
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String uid = null;
	private String name = ""; // name must be unique, hence it can be used as primary key
	private String password = "";
	private int victories = 0;
	private int defeats = 0;
    private String lastGame = null;

    /**
     * Create a new user object.
     *
     * @param uid user ID
     * @param name user name
     * @param password user password
     */
	public User(String uid, String name, String password) {
        this.setUid(uid);
		this.setName(name);
		this.setPassword(password);
	}

    /**
     * Get the user ID.
     *
     * @return user ID
     */
    public String getUid() {
        return uid;
    }

    /**
     * Set the user ID
     *
     * @param uid user ID
     */
    void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Set the user name.
     *
     * @param name user name
     */
    void setName(String name) {
		this.name = name;
	}

    /**
     * Get the user name.
     *
     * @return user name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the password.
     *
     * @param password password
     */
    void setPassword(String password) {
		this.password = password;
	}

    /**
     * Get the password.
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get the last name.
     *
     * @return last name
     */
    public String getLastGame() {
        return lastGame;
    }

    /**
     * Set the last name.
     *
     * @param lastGame last name
     */
    public void setLastGame(String lastGame) {
        this.lastGame = lastGame;
    }

    /**
     * Get defeat count.
     *
     * @return defeats
     */
    public int getDefeats() {
        return defeats;
    }

    /**
     * Increase defeat count by 1.
     */
    public void addDefeat() {
        this.defeats++;
        UserFile dUserFile = new UserFile();
        try {
        dUserFile.store(this);
        } catch (Exception ignore) {

        }
    }

    /**
     * Get victory count.
     *
     * @return victories
     */
    public int getVictories() {
        return victories;
    }

    /**
     * Increase victory count by 1.
     */
    public void addVictory() {
        this.victories++;
        UserFile dUserFile = new UserFile();
        try {
            dUserFile.store(this);
        } catch (Exception ignore) {

        }
    }

    /**
     * Count all played games.
     *
     * @return game count
     */
    public int getGamesNr() {
    	return victories+defeats;
    }

    /**
     * Get the difference between victories and defeats.
     *
     * @return difference between victories and defeats
     */
    public int getDifference() {
    	return victories-defeats;
    }
}
