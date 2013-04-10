/**
 * 
 */
package battleship.data;

import java.util.ArrayList;

import battleship.objects.User;

/**
 * @author Hinrich Kaestner
 *
 */
public interface Player {
	boolean add(User user);
	User getUser(int uid);
	ArrayList<User> getAll();
}
