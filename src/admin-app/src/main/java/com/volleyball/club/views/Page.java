package com.volleyball.club.views;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Page extends JPanel{
    public Page(){
        super();
        setSize(1024,512);
        setVisible(true);
        add(new JLabel("Page fonctionnelle"));
    }
}
