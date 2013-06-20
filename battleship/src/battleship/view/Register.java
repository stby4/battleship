package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import battleship.objects.DuplicateUsersException;
import battleship.objects.IncompleteDataException;
import battleship.objects.NotMatchingPasswordsException;

/**
 * Registrierung Battleship
 * @author Tom Ohme
 *
 */
public class Register extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Application app;

    private JLabel bLabel = new JLabel("Username: ");
    private JLabel pLabel = new JLabel("Password: ");
    private JLabel pLabel2 = new JLabel("Password: ");
    private JTextField bField = new JTextField();
    private JPasswordField pField = new JPasswordField();
    private JPasswordField pField2 = new JPasswordField();
    private JButton register = new JButton("Register");
    private JButton back = new JButton("Back");
    private JButton quit = new JButton("Quit");
    private JPanel registerPanel = new JPanel();

    /**
     * Register
     *
     * @param app Application
     */
    public Register(Application app) {
        this.app = app;

        setTitle("Registrierung Battleship");
        //Centering of the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top = (screenSize.height - 200) / 2;
        int left = (screenSize.width - 400) / 2;
        setSize(400, 200);
        setLocation(left, top);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.lightGray);
        setResizable(false);

        registerPanel.setLayout(null);

        bLabel.setBounds(50, 15, 100, 20);
        bField.setBounds(150, 15, 180, 20);
        pLabel.setBounds(50, 45, 100, 20);
        pField.setBounds(150, 45, 180, 20);
        pLabel2.setBounds(50, 75, 100, 20);
        pField2.setBounds(150, 75, 180, 20);
        register.setBounds(200, 110, 128, 20);
        back.setBounds(50, 110, 128, 20);
        quit.setBounds(200, 140, 128, 20);

        registerPanel.add(bLabel);
        registerPanel.add(bField);
        registerPanel.add(pLabel);
        registerPanel.add(pField);
        registerPanel.add(pLabel2);
        registerPanel.add(pField2);
        registerPanel.add(register);
        registerPanel.add(back);
        registerPanel.add(quit);

        getContentPane().add(registerPanel);

        actionregister();
    }

    public void actionregister() {
        getRootPane().setDefaultButton(register);
        register.addActionListener(this);
        back.addActionListener(this);
        quit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == register) {
            String username = bField.getText();
            char[] charpassword = pField.getPassword();
            char[] charpassword2 = pField2.getPassword();
            String password = new String(charpassword);
            String password2 = new String(charpassword2);
            battleship.logic.User lUser = battleship.logic.User.getInstance();
            try {
                lUser.createNewUser(username, password, password2);
                JOptionPane.showMessageDialog(null, "Welcome to battleship!");
                emptyFields();
                app.menu();
            } catch (DuplicateUsersException due) { // user name already taken
                JOptionPane.showMessageDialog(null, due.getMessage());
            } catch (NotMatchingPasswordsException nmpe) { // password fields do not match
                JOptionPane.showMessageDialog(null, nmpe.getMessage());
            } catch (IncompleteDataException ide) { // user left some data fields clear
                JOptionPane.showMessageDialog(null, ide.getMessage());
            } catch (IOException ioe) { // error in saving the data
                JOptionPane.showMessageDialog(null, "There was an error while storing your data. Please try again.");
            }
        } else if (e.getSource() == back) {
            emptyFields();
            app.login();
        } else if (e.getSource() == quit) {
            System.exit(0);
        }
    }

    //text fields empty
    public void emptyFields() {
        bField.setText("");
        pField.setText("");
        pField2.setText("");
    }

}
