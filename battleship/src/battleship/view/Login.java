package battleship.view;

import battleship.logic.UserManagement;
import battleship.objects.IncompleteDataException;
import battleship.objects.WrongCredentialsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * login authentication Panel
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class Login extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Application app;

    private JLabel bLabel = new JLabel("Username: ");
    private JLabel pLabel = new JLabel("Password: ");
    private JTextField bField = new JTextField();
    private JPasswordField pField = new JPasswordField();
    private JButton login = new JButton("Login");
    private JButton signUp = new JButton("Sign up");
    private JButton quit = new JButton("Quit");
    private JPanel loginPanel = new JPanel();

    /**
     * login panel with 2 input fields and 3 buttons
     *
     * @param app
     */
    Login(Application app) {
        this.app = app;

        setTitle("Login Authentication Battleship");
        //Centering of the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top = (screenSize.height - 200) / 2;
        int left = (screenSize.width - 400) / 2;
        setSize(400, 200);
        setLocation(left, top);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.lightGray);
        setResizable(false);

        loginPanel.setLayout(null);

        bLabel.setBounds(50, 30, 100, 20);
        bField.setBounds(150, 30, 180, 20);
        pLabel.setBounds(50, 60, 100, 20);
        pField.setBounds(150, 60, 180, 20);
        login.setBounds(200, 110, 128, 20);
        signUp.setBounds(50, 110, 128, 20);
        quit.setBounds(200, 140, 128, 20);

        loginPanel.add(bLabel);
        loginPanel.add(bField);
        loginPanel.add(pLabel);
        loginPanel.add(pField);
        loginPanel.add(login);
        loginPanel.add(signUp);
        loginPanel.add(quit);

        getContentPane().add(loginPanel);

        actionLogin();
    }

    /**
     * setDefaultButton
     * add ActionListener on login, signUp and exit
     */
    private void actionLogin() {
        getRootPane().setDefaultButton(login);
        login.addActionListener(this);
        signUp.addActionListener(this);
        quit.addActionListener(this);
    }

    /**
     * login Button if user exist he comes to the menu
     * signUp Button if user not exist he comes to the register
     * quit Button leave the Login
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = bField.getText();
            char[] charpassword = pField.getPassword();
            String password = new String(charpassword);
            UserManagement lUserManagement = UserManagement.getInstance();
            try {
                lUserManagement.login(username, password);
                app.loginDone();
            } catch (IncompleteDataException ide) {
                JOptionPane.showMessageDialog(null, ide.getMessage());
            } catch (WrongCredentialsException wce) {
                JOptionPane.showMessageDialog(null, wce.getMessage());
            } finally {
                emptyFields();
            }
        } else if (e.getSource() == signUp) {
            app.register();
            emptyFields();
        } else if (e.getSource() == quit) {
            System.exit(0);
        }
    }

    /**
     * empties the fields
     */
    public void emptyFields() {
        bField.setText("");
        pField.setText("");
    }

}
