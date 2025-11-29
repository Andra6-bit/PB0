package ui;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import database.DatabaseManager;
import models.Freelancer;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private DatabaseManager db;

    public LoginFrame() {
        db = new DatabaseManager();

        setTitle("Freelance Marketplace - Login");
        setSize(420, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel(new GridBagLayout());
        main.setBackground(new Color(35, 25, 55));
        main.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(main);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("LOGIN PORTAL", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        main.add(title, gbc);
        gbc.gridwidth=1;

        main.add(lbl("Username:"), pos(gbc,0,1));
        JTextField user = new JTextField();
        main.add(user, pos(gbc,1,1));

        main.add(lbl("Password:"), pos(gbc,0,2));
        JPasswordField pass = new JPasswordField();
        main.add(pass, pos(gbc,1,2));

        main.add(lbl("Login as:"), pos(gbc,0,3));
        JComboBox<String> role = new JComboBox<>(new String[]{"Admin","Freelancer","HRD"});
        main.add(role, pos(gbc,1,3));

        JButton btn = new JButton("LOGIN");
        btn.setBackground(new Color(140,70,200));
        btn.setForeground(Color.WHITE);
        main.add(btn, pos(gbc,0,4,2));

        // DEBUG output to console to help trace values
        btn.addActionListener(e -> {
            String u = user.getText().trim();
            String p = new String(pass.getPassword());
            String r = (String) role.getSelectedItem();

            System.out.println("=== DEBUG LOGIN ===");
            System.out.println("Username input = [" + u + "]");
            System.out.println("Password input = [" + p + "]");
            System.out.println("Role selected = [" + r + "]");

            if ("Admin".equals(r) && "admin".equals(u) && "admin123".equals(p)) {
                new AdminDashboard(db).setVisible(true);
                dispose();
                return;
            }

            if ("HRD".equals(r) && "hrd".equals(u) && "hrd123".equals(p)) {
                new EmployerDashboard(db).setVisible(true);
                dispose();
                return;
            }

            if ("Freelancer".equals(r)) {
                Freelancer f = db.getFreelancerByName(u);
                if (f == null) {
                    JOptionPane.showMessageDialog(this, "Freelancer tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String expectedPassword = f.getName().toLowerCase().replace(" ", "") + "123";
                System.out.println("Expected pass = " + expectedPassword);

                if (!p.equals(expectedPassword)) {
                    JOptionPane.showMessageDialog(this, "Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(this, "Login sebagai Freelancer");
                new FreelancerDashboard(db, f).setVisible(true);
                dispose();
                return;
            }

            JOptionPane.showMessageDialog(this, "Login gagal!");
        });
    }

    private JLabel lbl(String t){
        JLabel l = new JLabel(t);
        l.setForeground(Color.WHITE);
        return l;
    }

    private GridBagConstraints pos(GridBagConstraints gbc, int x, int y){
        gbc.gridx=x; gbc.gridy=y; gbc.gridwidth=1;
        return gbc;
    }

    private GridBagConstraints pos(GridBagConstraints gbc, int x, int y, int w){
        gbc.gridx=x; gbc.gridy=y; gbc.gridwidth=w;
        return gbc;
    }

    public static void main(String[] args) {
        FlatDarkPurpleIJTheme.setup();
        new LoginFrame().setVisible(true);
    }
}
