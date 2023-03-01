package opondo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class customerPortal extends JFrame {
    private JTextField tfsearch;
    private JButton searchButton;
    private JButton btnCancel;
    private JPanel csPanel;
    private JTextField tfItemOb;
    private JTextField tfPriceOb;
    private JButton btnOrder;


    public customerPortal() {

            setTitle("Customer Portal");
            setContentPane(csPanel);
            setMinimumSize(new Dimension(450, 500));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    final String DRIVER = "com.mysql.jdbc.Driver";
                    final String DATABASE_URL = "jdbc:mysql://localhost:3306/duka_db";
                    final String USER = "root";
                    final String PASSWORD = "";
                    Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                    Statement stmt = conn.createStatement();
                    String sql = "SELECT * FROM Items WHERE Item=?";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    String Item = tfsearch.getText();
                    preparedStatement.setString(1, Item);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (!resultSet.next()){
                        JOptionPane.showMessageDialog(searchButton,"sorry record not found");
                        tfItemOb.setText("");
                        tfItemOb.setText("");
                        tfsearch.requestFocus();
                    }else {
                        tfItemOb.setText(resultSet.getString("Item"));
                        tfPriceOb.setText(resultSet.getString("Price"));
                    }
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        });
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    final String DRIVER = "com.mysql.jdbc.Driver";
                    final String DATABASE_URL = "jdbc:mysql://localhost:3306/duka_db";
                    final String USER = "root";
                    final String PASSWORD = "";
                    Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                    String sql = " insert into Orders (Item,Price)"
                            + " values (?, ?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    String Item = tfItemOb.getText();
                    String Price = tfPriceOb.getText();
                    preparedStatement.setString(1, Item);
                    preparedStatement.setString(2, Price);

                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null," Order placed");
                    new Processed().setVisible(true);
                    dispose();
                    conn.close();



                }catch (Exception e){
                    throw new RuntimeException(e);

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
        customerPortal cs = new customerPortal();
        cs.setContentPane(cs.csPanel);
        cs.setTitle("Customer Portal");
        cs.setSize(400,450);
        cs.setVisible(true);
        cs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void Connect(){
        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/duka_db";
        final String USER = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

