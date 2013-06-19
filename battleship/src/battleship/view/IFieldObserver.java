package battleship.view;

/**
 * Observer interface for mouse clicks on field
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public interface IFieldObserver {
    void fieldClicked(int x, int y);
}
