package opondo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Processed extends JFrame {
    private JTable tblProcessed;
    private JPanel panelProcessed;
    private JButton btnProcessed;
    private JButton btnCancel;

    public Processed() {
        setTitle("Processed Orders");
        setContentPane(panelProcessed);
        setMinimumSize(new Dimension(450, 800));
        btnProcessed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    final String DRIVER = "com.mysql.jdbc.Driver";
                    final String DATABASE_URL = "jdbc:mysql://localhost:3306/duka_db";
                    final String USER = "root";
                    final String PASSWORD = "";
                    Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                    Statement stmt = conn.createStatement();
                    String sql = "SELECT * FROM Orders";
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()){

                        String Item = rs.getString("Item");
                        String Price = rs.getString("Price");
                        //string array for store data into table
                        String tbData[] ={Item,Price};
                        DefaultTableModel tableModel = (DefaultTableModel) tblProcessed.getModel();
                        tableModel.addColumn(tbData);
                        tableModel.addRow(tbData);

                        JOptionPane.showMessageDialog(null,"Here are the processed orders");



                    }

                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Processed ps = new Processed();
        ps.setContentPane(ps.panelProcessed);
        ps.setTitle("Customer Portal");
        ps.setSize(400,450);
        ps.setVisible(true);
        ps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ps.setLocationRelativeTo(ps.panelProcessed);
    }
}