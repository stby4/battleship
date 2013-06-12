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
    private String lastGame = null;

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

    void setUid(String uid) {
        this.uid = uid;
    }

    /**
     *
     * @param name
     */
    void setName(String name) {
		this.name = name;
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
    void setPassword(String password) {
		this.password = password;
	}

    /**
     *
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    public String getLastGame() {
        return lastGame;
    }

    public void setLastGame(String lastGame) {
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
