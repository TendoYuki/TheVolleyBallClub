package com.volleyball.club.views;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class LoginPage extends Page{
    public LoginPage() {
        super();
        setLayout(new GridBagLayout());
        
        Border padding = BorderFactory.createEmptyBorder(50, 250, 50, 250);

        setBorder(padding);

        JLabel LEMail = new JLabel("E-Mail");
        JLabel LPassword = new JLabel("Password");

        JPasswordField TFPassword = new JPasswordField();
        JTextField TFEmail = new JTextField();

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill= GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gc.insets = new Insets(20, 20, 0, 20);

        gc.gridy = 0;
        gc.gridx = 0;
        add(LEMail, gc);
        gc.gridx = 1;
        add(TFEmail, gc);

        gc.gridy = 1;
        gc.gridx = 0;
        add(LPassword, gc);
        gc.gridx = 1;
        add(TFPassword, gc);

        JButton BTNSignIn = new JButton("Sign in");
        gc.gridy = 2;
        gc.gridx = 0;
        add(BTNSignIn, gc);
    }
}
