/**
 * 
 */
package battleship.data;

import java.util.ArrayList;

import battleship.objects.User;

/**
 * @author Tom Ohme
 *
 */
public interface Player {
	boolean add(User user);
	User getUser(int uid);
	ArrayList<User> getAll();
}

// TODO a lot...  (Tom)