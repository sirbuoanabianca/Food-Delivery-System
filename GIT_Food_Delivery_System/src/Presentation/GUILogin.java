package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUILogin extends JFrame{
    //panou de logare sau inregistrare
    private JPanel panel1;
    private JTextField userName;
    private JPasswordField password;
    private JButton login;
    private JButton register;

    public GUILogin()  {
        add(panel1);
        setMinimumSize(new Dimension(400,400));
    }

    public void setLoginButtonActionListener(ActionListener actionListener)
    {
        login.addActionListener(actionListener);
    }

    public void setRegisterButtonActionListener(ActionListener actionListener)
    {
        register.addActionListener(actionListener);
    }

    public JTextField getUserName() {
        return userName;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void showWrongDataMessage(String s)
    {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, s);
    }
}
