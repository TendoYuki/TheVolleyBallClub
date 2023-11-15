package com.volleyball.club.views;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.volleyball.club.database.DBConnectionManager;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.GridBagConstraints;

public class LoginPage extends Page{
        JLabel LLogin ;
        JLabel LPassword;

        JPasswordField TFPassword;
        JTextField TFLogin;

    public LoginPage() {
        super();
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
        String query = "SELECT * FROM admin";
        ResultSet resSet = DBConnectionManager.execQuery(query);
        String password = TFPassword.getText();
        String login = TFLogin.getText();
        System.out.println(password);
        System.out.println(login);
        String dbLogin="",dbPassword="";
        boolean connected = false;
        try{
            while(resSet.next()){
                dbLogin = resSet.getString("loginAdmin");
                dbPassword = resSet.getString("passwordAdmin");
                if(login.equals(dbLogin.toLowerCase()) && password.equals(dbPassword)) connected = true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Connected : " + connected);
        revalidate();
        repaint();
    }
}
