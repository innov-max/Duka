package opondo;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class login extends JDialog {
    private JTextField tfName;
    private JTextField tfPassword;
    private JButton btnContinue;
    private JButton btnCancel;
    private JPanel LoginWindow;

    public login() {

        setTitle("Duka Login Page");
        setContentPane(LoginWindow);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);

        btnContinue.addActionListener(actionEvent -> {
            String name = tfName.getText();
            String password = tfPassword.getText();
            user = getAuthenticatedUser(name, password);
            if (user != null) {
                JOptionPane.showMessageDialog(null,"Welcome "+ name);
                new AdminPortal().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(login.this,
                        "Name or Password is invalid", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
        btnCancel.addActionListener(actionEvent ->
                dispose());
        setVisible(true);
    }

    public static User user;

    private User getAuthenticatedUser(String name, String password) {
        User user = null;
        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/duka_db";
        final String USER = "root";
        final String PASSWORD = "";
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE name=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("name");
                user.password = resultSet.getString("password");
            }
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }



    public static void main(String[] args) {
        login Login = new login();
        User user = Login.user;
        if (user != null) {
            System.out.println("successful Authentication of:" + user.name);


        } else {
            System.out.println("authentication cancelled");
        }
    }
}
