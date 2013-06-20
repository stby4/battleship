package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import battleship.objects.IncompleteDataException;
import battleship.objects.WrongCredentialsException;

/**
 * Login Battleship
 *
 * @author Tom Ohme
 */
public class Login extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Application app;

    private JLabel bLabel = new JLabel("Username: ");
    private JLabel pLabel = new JLabel("Password: ");
    private JTextField bField = new JTextField();
    private JPasswordField pField = new JPasswordField();
    private JButton login = new JButton("Login");
    private JButton register = new JButton("Register");
    private JButton quit = new JButton("Quit");
    private JPanel loginPanel = new JPanel();

    Login(Application app) {
        this.app = app;

        setTitle("Login Autentification Battleship");
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
        register.setBounds(50, 110, 128, 20);
        quit.setBounds(200, 140, 128, 20);

        loginPanel.add(bLabel);
        loginPanel.add(bField);
        loginPanel.add(pLabel);
        loginPanel.add(pField);
        loginPanel.add(login);
        loginPanel.add(register);
        loginPanel.add(quit);

        getContentPane().add(loginPanel);

        actionLogin();
    }

    private void actionLogin() {
        getRootPane().setDefaultButton(login);
        login.addActionListener(this);
        register.addActionListener(this);
        quit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = bField.getText();
            char[] charpassword = pField.getPassword();
            String password = new String(charpassword);
            battleship.logic.User lUser = battleship.logic.User.getInstance();
            try {
                lUser.login(username, password);
                app.loginDone();
            } catch (IncompleteDataException ide) {
                JOptionPane.showMessageDialog(null, ide.getMessage());
            } catch (WrongCredentialsException wce) {
                JOptionPane.showMessageDialog(null, wce.getMessage());
            } finally {
                emptyFields();
            }
        } else if (e.getSource() == register) {
            app.register();
            emptyFields();
        } else if (e.getSource() == quit) {
            System.exit(0);
        }
    }

    //text fields empty
    public void emptyFields() {
        bField.setText("");
        pField.setText("");
    }

}
