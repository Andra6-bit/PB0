package ui;

import database.DatabaseManager;
import models.ActivityLog;
import models.Freelancer;
import models.Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends JFrame {

    private DatabaseManager db;
    private CardLayout card;
    private JPanel contentPanel;

    private DefaultTableModel freelancerModel;
    private DefaultTableModel projectModel;
    private DefaultTableModel logModel;

    public AdminDashboard(DatabaseManager db) {
        this.db = db;

        setTitle("Admin Dashboard");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setLayout(new GridLayout(10, 1, 0, 10));
        sidebar.setBackground(new Color(40, 30, 70));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel title = new JLabel("ADMIN PANEL", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        sidebar.add(title);

        JButton btnFreelancer = sideButton("🧑‍💻  Manage Freelancers");
        JButton btnProject = sideButton("📁  Manage Projects");
        JButton btnLogs = sideButton("📜  Activity Logs");
        JButton btnLogout = sideButton("🚪  Logout");

        sidebar.add(btnFreelancer);
        sidebar.add(btnProject);
        sidebar.add(btnLogs);
        sidebar.add(new JLabel(""));
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        card = new CardLayout();
        contentPanel = new JPanel(card);
        contentPanel.setBackground(new Color(30, 20, 50));
        add(contentPanel, BorderLayout.CENTER);

        contentPanel.add(createFreelancerPanel(), "FREELANCERS");
        contentPanel.add(createProjectPanel(), "PROJECTS");
        contentPanel.add(createLogPanel(), "LOGS");

        btnFreelancer.addActionListener(e -> { loadFreelancers(); card.show(contentPanel, "FREELANCERS"); });
        btnProject.addActionListener(e -> { loadProjects(); card.show(contentPanel, "PROJECTS"); });
        btnLogs.addActionListener(e -> { loadLogs(); card.show(contentPanel, "LOGS"); });

        btnLogout.addActionListener(e -> { new LoginFrame().setVisible(true); dispose(); });
    }

    private JButton sideButton(String text) {
        JButton b = new JButton(text);
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(90, 60, 150));
        b.setFocusPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
        return b;
    }

    private JPanel createFreelancerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(35, 25, 60));

        JLabel title = new JLabel("🧑‍💻 Manage Freelancers", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        panel.add(title, BorderLayout.NORTH);

        freelancerModel = new DefaultTableModel(new String[]{
                "Name", "Skill", "Email", "Rate", "Experience"
        }, 0);

        JTable table = new JTable(freelancerModel);
        styleTable(table);

        JScrollPane sp = new JScrollPane(table);
        sp.getViewport().setBackground(new Color(45, 35, 80));
        panel.add(sp, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(35, 25, 60));

        JButton btnRefresh = btn("🔄 Refresh");
        JButton btnDelete = btn("🗑 Delete");

        btnRefresh.addActionListener(e -> loadFreelancers());

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Select a freelancer!");
                return;
            }

            String name = (String) freelancerModel.getValueAt(row, 0);
            Freelancer f = db.getFreelancerByName(name);

            if (f == null) {
                JOptionPane.showMessageDialog(this, "Data not found!");
                return;
            }

            db.deleteFreelancer(f.getId());
            loadFreelancers(); // <-- small typo in this line, fix below
        });

        // The above anonymous action had a small accidental split line (editor paste). Replace entire delete action with correct code:
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Select a freelancer!"); return; }
            String name = (String) freelancerModel.getValueAt(row, 0);
            Freelancer f = db.getFreelancerByName(name);
            if (f == null) { JOptionPane.showMessageDialog(this, "Data not found!"); return; }
            db.deleteFreelancer(f.getId());
            loadFreelancers();
        });

        btnPanel.add(btnRefresh);
        btnPanel.add(btnDelete);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createProjectPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(35, 25, 60));

        JLabel title = new JLabel("📁 Manage Projects", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        panel.add(title, BorderLayout.NORTH);

        projectModel = new DefaultTableModel(new String[]{
                "Title", "Company", "Skill", "Budget", "Progress"
        }, 0);

        JTable table = new JTable(projectModel);
        styleTable(table);

        JScrollPane sp = new JScrollPane(table);
        sp.getViewport().setBackground(new Color(45, 35, 80));
        panel.add(sp, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(35, 25, 60));

        JButton btnRefresh = btn("🔄 Refresh");
        JButton btnDelete = btn("🗑 Delete");

        btnRefresh.addActionListener(e -> loadProjects());

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Select a project!"); return; }
            String titleProj = (String) projectModel.getValueAt(row, 0);
            Project p = db.findProjectByTitle(titleProj);
            if (p == null) { JOptionPane.showMessageDialog(this, "Project not found!"); return; }
            db.deleteProject(p.getId());
            loadProjects();
        });

        btnPanel.add(btnRefresh);
        btnPanel.add(btnDelete);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createLogPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(35, 25, 60));

        JLabel title = new JLabel("📜 Activity Logs", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        panel.add(title, BorderLayout.NORTH);

        logModel = new DefaultTableModel(new String[]{"Timestamp", "User", "Action", "Detail"}, 0);
        JTable table = new JTable(logModel);
        styleTable(table);
        JScrollPane sp = new JScrollPane(table);
        panel.add(sp, BorderLayout.CENTER);

        return panel;
    }

    private void loadFreelancers() {
        freelancerModel.setRowCount(0);
        List<Freelancer> list = db.getAllFreelancers();
        for (Freelancer f : list) {
            freelancerModel.addRow(new Object[]{f.getName(), f.getSkill(), f.getEmail(), f.getRatePerHour(), f.getExperience()});
        }
    }

    private void loadProjects() {
        projectModel.setRowCount(0);
        List<Project> list = db.getAllProjects();
        for (Project p : list) {
            projectModel.addRow(new Object[]{p.getTitle(), p.getCompanyName(), p.getRequiredSkills(), p.getBudget(), p.getProgress()});
        }
    }

    private void loadLogs() {
        logModel.setRowCount(0);
        List<ActivityLog> logs = db.getActivityLogs();
        for (ActivityLog l : logs) {
            logModel.addRow(new Object[]{l.getTimestamp(), l.getUser(), l.getAction(), l.getDetail()});
        }
    }

    private JButton btn(String t) {
        JButton b = new JButton(t);
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(100, 50, 150));
        b.setFocusPainted(false);
        return b;
    }

    private void styleTable(JTable table) {
        table.setBackground(new Color(45, 35, 80));
        table.setForeground(Color.WHITE);
        table.setRowHeight(28);
        table.setGridColor(Color.GRAY);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
    }
}
