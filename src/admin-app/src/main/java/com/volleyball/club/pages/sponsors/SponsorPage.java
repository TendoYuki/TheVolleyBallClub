package com.volleyball.club.pages.sponsors;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.volleyball.club.database.DBConnectionManager;
import com.volleyball.club.pages.Page;

public class SponsorPage extends Page{
    private static DefaultTableModel defaultTable = new DefaultTableModel(new String[]{"ID","Name","Logo"},0){
        @Override
        public boolean isCellEditable(int row, int column) {
            // Make all cells non-editable
            return false;
        }
    };
    
    private static JTable table;

    public SponsorPage(){
        super();
        JPanel tdisplay = new JPanel();
        tdisplay.add(new SponsorEditPage());
        JButton submit = new JButton("submit");
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        tdisplay.add(submit);
        table = new JTable(defaultTable);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setMinimumSize(new Dimension(500, 500));
        add(scroll,BorderLayout.CENTER);
        add(tdisplay,BorderLayout.SOUTH);
        add(new JLabel("Sponsor Page"), BorderLayout.NORTH);
    }
    
    public void loadResults(){
        String query = "SELECT * FROM partner";
        ResultSet resSet = DBConnectionManager.execQuery(query);
        defaultTable.setRowCount(0);
        String name="",logo="", id="";
        JButton delete = new JButton("delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int res = JOptionPane.showConfirmDialog(null,"Etes-vous sur ?");

                if(res == JOptionPane.YES_OPTION){
                    System.out.println("YES OPTION SELECTED");
                    int selectedRow = table.getSelectedRow();
                    if(selectedRow != -1){
                        String id = (String)defaultTable.getValueAt(selectedRow, 0);
                        System.out.println(id);
                    }
                }
            }
        });
        try{
            while(resSet.next()){
                name = resSet.getString("namePartner");
                logo = resSet.getString("logoPartner");
                id = resSet.getString("idPartner");
                defaultTable.addRow(new String[]{id,name,logo});
            }
        }catch(Exception e){
            System.out.println(e);
        } 
        table.setModel(defaultTable);
        revalidate();
        repaint();
    }
}
