package battleship.objects;

/**
 * User class
 *
 * @author H. Kaestner
 */
public class User implements java.io.Serializable {
    private String uid = null;
	private String name = ""; // name must be unique, hence it can be used as primary key
	private String password = "";
	private int victories = 0;
	private int defeats = 0;
    private int lastGame = -1;

    /**
     *
     * @param name
     * @param password
     */
	public User(String uid, String name, String password) {
        this.setUid(uid);
		this.setName(name);
		this.setPassword(password);
	}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     *
     * @param name
     * @return
     */
	public boolean setName(String name) {
		this.name = name;
		return true;
	}

    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }
	
	/*
	 * @param String password
	 * @return boolean password successful set
	 */
	public boolean setPassword(String password) {
		this.password = password;
		return true;
	}

    /**
     *
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    public int getLastGame() {
        return lastGame;
    }

    public void setLastGame(int lastGame) {
        this.lastGame = lastGame;
    }

    public int getDefeats() {
        return defeats;
    }

    public void addDefeat() {
        this.defeats++;
    }

    public int getVictories() {
        return victories;
    }

    public void addVictory() {
        this.victories++;
    }
}
