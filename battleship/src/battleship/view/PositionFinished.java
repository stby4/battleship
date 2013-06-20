package battleship.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * positionFinished Panel
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class PositionFinished extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Application app;

    JButton next = new JButton("NEXT");
    JPanel positionFinishedPanel = new JPanel();

    /**
     * show a picture before the game starts
     *
     * @param app
     */
    public PositionFinished(Application app) {
        this.app = app;

        setTitle("Ready for Battleship");
        //Centering of the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top = (screenSize.height - 400) / 2;
        int left = (screenSize.width - 600) / 2;
        setSize(600, 400);
        setLocation(left, top);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setBackground(Color.BLACK);
        setResizable(false);

        positionFinishedPanel.setLayout(null);

        next.setBounds(250, 325, 110, 25);

        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFocusable(false);

        JPanel picture = new PicturePanel("schiff.jpg", 600, 399);
        picture.setBounds(0, 0, 600, 399);
        positionFinishedPanel.add(picture);
        positionFinishedPanel.add(next);

        positionFinishedPanel.setComponentZOrder(picture, 1);
        positionFinishedPanel.setComponentZOrder(next, 0);

        positionFinishedPanel.setBackground(Color.BLACK);

        getContentPane().add(positionFinishedPanel);

        actionMenu();
    }

    /**
     * add ActionListener on next
     */
    public void actionMenu() {
        next.addActionListener(this);
    }

    /**
     * next Button the user comes to the game
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            app.game();
        }  
    }

}
