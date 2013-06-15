package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * PositionFinished Battleship
 * @author Tom Ohme
 *
 */
public class PositionFinished extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Application app;

    JButton next = new JButton("NEXT");
    JPanel positionFinishedPanel = new JPanel();

    public PositionFinished(Application app) {
        this.app = app;

        setTitle("Ready for Battleship");
        //Centering of the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top = (screenSize.height - 400) / 2;
        int left = (screenSize.width - 600) / 2;
        setSize(600, 400);
        setLocation(left, top);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public void actionMenu() {
        next.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            app.game();
        }  
    }

}
