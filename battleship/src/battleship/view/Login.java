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

    JLabel bLabel = new JLabel("Username: ");
    JLabel pLabel = new JLabel("Password: ");
    JTextField bField = new JTextField();
    JPasswordField pField = new JPasswordField();
    JButton anmelden = new JButton("Anmelden");
    JButton registrieren = new JButton("Registrieren");
    JButton beenden = new JButton("Beenden");
    JPanel loginPanel = new JPanel();

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
        anmelden.setBounds(200, 110, 128, 20);
        registrieren.setBounds(50, 110, 128, 20);
        beenden.setBounds(200, 140, 128, 20);

        loginPanel.add(bLabel);
        loginPanel.add(bField);
        loginPanel.add(pLabel);
        loginPanel.add(pField);
        loginPanel.add(anmelden);
        loginPanel.add(registrieren);
        loginPanel.add(beenden);

        getContentPane().add(loginPanel);

        actionlogin();
    }

    public void actionlogin() {
        getRootPane().setDefaultButton(anmelden);
        anmelden.addActionListener(this);
        registrieren.addActionListener(this);
        beenden.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == anmelden) {
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
        } else if (e.getSource() == registrieren) {
            app.register();
            emptyFields();
        } else if (e.getSource() == beenden) {
            System.exit(0);
        }
    }

    //text fields empty
    public void emptyFields() {
        bField.setText("");
        pField.setText("");
    }

}
