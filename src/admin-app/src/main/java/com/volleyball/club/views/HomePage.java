package com.volleyball.club.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.volleyball.club.database.DBConnectionManager;

public class HomePage extends Page{

    public HomePage(){
        super();
        JLabel logo = new JLabel("",SwingConstants.CENTER);
        logo.setSize(new Dimension(250,250));
        setLayout(new BorderLayout());
        add(new JLabel("VolleyBall Club", SwingConstants.CENTER), BorderLayout.NORTH);
        String filePath = new File("").getAbsolutePath();
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filePath + "/../../public/logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(),Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(dimg));

        add(logo, BorderLayout.CENTER);
    
    }
}
