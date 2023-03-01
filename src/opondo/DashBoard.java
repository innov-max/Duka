package opondo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DashBoard extends JDialog{

    private JButton ADMINButton;
    private JButton USERButton;
    private JPanel panelDash;

    public DashBoard(JFrame parent) {
        super(parent);
        setTitle("Duka Login Page");
        setContentPane(panelDash);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
    ADMINButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new login().setVisible(true);
            dispose();
        }
    });
        USERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new customerPortal().setVisible(true);
                dispose();

            }
        });
    }
    public static void main(String[] args) {
        DashBoard cs = new DashBoard(null);
        cs.setContentPane(cs.panelDash);
        cs.setTitle("Customer Portal");
        cs.setSize(400,450);
        cs.setVisible(true);
        cs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
