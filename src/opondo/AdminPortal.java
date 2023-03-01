package opondo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class AdminPortal extends JFrame {
    private JLabel lblIcon;
    private JTextField tFItemAdmin;
    private JTextField tfPriceAdmin;
    private JButton UPDATEButton;
    private JButton btnDelete;
    private JPanel adminPanel;
    private JButton INSERTButton;
    private JButton btnExit;

    public AdminPortal(){

        setTitle("ADMIN PANEL");
        setContentPane(adminPanel);
        setMinimumSize(new Dimension(450, 500));
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final String DRIVER = "com.mysql.jdbc.Driver";
                final String DATABASE_URL = "jdbc:mysql://localhost:3306/duka_db";
                final String USER = "root";
                final String PASSWORD = "";
                try {
                    Connection conn = DriverManager.getConnection(DATABASE_URL, "root", "");
                    String sql = "DELETE FROM Items";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                   preparedStatement.setString(1,tfPriceAdmin.getText());
                    preparedStatement.setString(2,tfPriceAdmin.getText());
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Deleted Successfully");
                    conn.close();

                }catch (Exception e){
                    throw new RuntimeException(e);

            }
            }
        });
        INSERTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    final String DRIVER = "com.mysql.jdbc.Driver";
                    final String DATABASE_URL = "jdbc:mysql://localhost:3306/duka_db";
                    final String USER = "root";
                    final String PASSWORD = "";
                    Connection conn = DriverManager.getConnection(DATABASE_URL, "root", "");
                    String sql = " insert into Items (Item,Price)"
                            + " values (?, ?)";
                    PreparedStatement preparedStmt = conn.prepareStatement(sql);
                    String Item = tFItemAdmin.getText();
                    String Price = tfPriceAdmin.getText();
                    preparedStmt.setString (1, Item);
                    preparedStmt.setString (2, Price);
                    preparedStmt.execute();
                    JOptionPane.showMessageDialog(null,"Item added successfully");
                    conn.close();


                }catch (Exception e){
                    throw new RuntimeException(e);
                }

            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new customerPortal().setVisible(true);
                dispose();
            }
        });
    }
    public void theQuery(String query){
        Connection con = null;
        Statement st = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/duka_db","root","");
            st = con.createStatement();
            st.executeQuery(query);
            JOptionPane.showMessageDialog(null,"executed successfully");
        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }
    public static void main(String[] args) {
        AdminPortal Ad = new AdminPortal();
        Ad.setContentPane(Ad.adminPanel);
        Ad.setTitle("Admin Portal");
        Ad.setSize(400,450);
        Ad.setVisible(true);
        Ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
