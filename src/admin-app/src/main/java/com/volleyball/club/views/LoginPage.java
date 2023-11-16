package com.volleyball.club.views;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.volleyball.club.login.LoginController;
import com.volleyball.club.login.exceptions.IncorrectLoginException;
import com.volleyball.club.login.exceptions.IncorrectPasswordException;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;

public class LoginPage extends Page{
    private JLabel LLogin ;
    private JLabel LPassword;

    private JPasswordField TFPassword;
    private JTextField TFLogin;
    private JFrame frame;

    public LoginPage(JFrame frame) {
        super();
        this.frame = frame;
        setLayout(new GridBagLayout());
        
        Border padding = BorderFactory.createEmptyBorder(50, 250, 50, 250);

        setBorder(padding);

        LLogin = new JLabel("Login");
        LPassword = new JLabel("Password");

        TFPassword = new JPasswordField();
        TFLogin = new JTextField();

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill= GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gc.insets = new Insets(20, 20, 0, 20);

        gc.gridy = 0;
        gc.gridx = 0;
        add(LLogin, gc);
        gc.gridx = 1;
        add(TFLogin, gc);

        gc.gridy = 1;
        gc.gridx = 0;
        add(LPassword, gc);
        gc.gridx = 1;
        add(TFPassword, gc);

        JButton BTNSignIn = new JButton("Sign in");
        BTNSignIn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
        BTNSignIn.setFocusPainted(false);
        gc.gridy = 2;
        gc.gridx = 0;
        add(BTNSignIn, gc);
    }

    public void connect(){
        try{
            LoginController.getInstance().authentify(TFLogin.getText(),String.copyValueOf(TFPassword.getPassword()));
        }
        catch(IncorrectLoginException e){
            JOptionPane.showMessageDialog(frame, "Login incorrect","Error", JOptionPane.ERROR_MESSAGE);
            TFLogin.setText("");
            TFPassword.setText("");
            return;
        }
        catch(IncorrectPasswordException e){
            JOptionPane.showMessageDialog(frame, "Password incorrect","Error", JOptionPane.ERROR_MESSAGE);
            TFPassword.setText("");
            return;
        }
        catch(Exception e){
            System.out.println(e);
            return;
        }
        JOptionPane.showMessageDialog(frame, "Logged in","Info", JOptionPane.INFORMATION_MESSAGE);
        TFLogin.setText("");
        TFPassword.setText("");
        revalidate();
        repaint();
    }
}
