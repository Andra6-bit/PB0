package ui;

import database.DatabaseManager;
import models.Freelancer;
import models.Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FreelancerDashboard extends JFrame {

    private DatabaseManager db;
    private Freelancer currentFreelancer;

    private DefaultTableModel availableModel;
    private JTable availableTable;

    private DefaultTableModel myModel;
    private JTable myTable;

    public FreelancerDashboard(DatabaseManager db, Freelancer f) {
        this.db = db;
        this.currentFreelancer = f;

        setTitle("Freelancer Dashboard - " + f.getName());
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ====================== SIDEBAR ==========================
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBackground(new Color(40, 30, 70));
        sidebar.setLayout(new GridLayout(10, 1, 0, 10));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel title = new JLabel(f.getName(), SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        sidebar.add(title);

        JButton btnProfile = sidebarBtn("My Profile");
        JButton btnAvailable = sidebarBtn("Available Projects");
        JButton btnTaken = sidebarBtn("My Projects");
        JButton btnLogout = sidebarBtn("Logout");

        sidebar.add(btnProfile);
        sidebar.add(btnAvailable);
        sidebar.add(btnTaken);
        sidebar.add(new JLabel(""));
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        // ====================== CONTENT =============================
        JPanel content = new JPanel(new CardLayout());
        content.setBackground(new Color(30, 20, 50));
        add(content, BorderLayout.CENTER);

        // ====================== PROFILE PANEL =======================
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(null);
        profilePanel.setBackground(new Color(35, 25, 60));

        JLabel lblTitleProfile = new JLabel("My Profile");
        lblTitleProfile.setForeground(Color.WHITE);
        lblTitleProfile.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitleProfile.setBounds(50, 20, 300, 30);
        profilePanel.add(lblTitleProfile);

        JLabel lblName = new JLabel("Nama: " + f.getName());
        lblName.setBounds(50, 80, 300, 30);
        lblName.setForeground(Color.WHITE);
        profilePanel.add(lblName);

        JLabel lblSkill = new JLabel("Skill: " + f.getSkill());
        lblSkill.setBounds(50, 120, 300, 30);
        lblSkill.setForeground(Color.WHITE);
        profilePanel.add(lblSkill);

        JLabel lblEmail = new JLabel("Email: " + f.getEmail());
        lblEmail.setBounds(50, 160, 300, 30);
        lblEmail.setForeground(Color.WHITE);
        profilePanel.add(lblEmail);

        JLabel lblRate = new JLabel("Rate per Hour: Rp " + f.getRatePerHour());
        lblRate.setBounds(50, 200, 300, 30);
        lblRate.setForeground(Color.WHITE);
        profilePanel.add(lblRate);

        JLabel lblExp = new JLabel("Experience: " + f.getExperience());
        lblExp.setBounds(50, 240, 350, 30);
        lblExp.setForeground(Color.WHITE);
        profilePanel.add(lblExp);


        // ====================== AVAILABLE PROJECT PANEL =============
        JPanel availablePanel = new JPanel(new BorderLayout());
        availablePanel.setBackground(new Color(30, 20, 50));

        availableModel = new DefaultTableModel(
                new String[]{"Title", "Company", "Skill", "Budget"}, 0
        );
        availableTable = new JTable(availableModel);
        styleTable(availableTable);

        JScrollPane spAvail = new JScrollPane(availableTable);
        availablePanel.add(spAvail, BorderLayout.CENTER);

        JPanel availBtnPanel = new JPanel();
        availBtnPanel.setBackground(new Color(30,20,50));
        JButton btnApply = new JButton("Apply / Take Project");
        JButton btnRefreshAvail = new JButton("Refresh");
        styleButton(btnApply);
        styleButton(btnRefreshAvail);
        availBtnPanel.add(btnApply);
        availBtnPanel.add(btnRefreshAvail);

        availablePanel.add(availBtnPanel, BorderLayout.SOUTH);

        // ======================= APPLY PROJECT =======================
        btnApply.addActionListener(e -> {
            int row = availableTable.getSelectedRow();

            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Pilih project dulu!");
                return;
            }

            Object raw = availableModel.getValueAt(row, 0);

            if (raw == null) {
                JOptionPane.showMessageDialog(this, "Judul project tidak valid!");
                return;
            }

            String projectTitle = raw.toString().trim();

            System.out.println("DEBUG TITLE = " + title);

            if (projectTitle.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Judul project kosong!");
                return;
            }

           Project p = db.findProjectByTitle(projectTitle);

            if (p == null) {
                JOptionPane.showMessageDialog(this, "Project tidak ditemukan di database!");
                return;
            }

            boolean ok = db.assignProjectToFreelancer(p.getId(), currentFreelancer.getId());

            if (ok) {
                db.updateProjectProgress(p.getId(), "Sedang Dikerjakan");
                JOptionPane.showMessageDialog(this, "Project berhasil diambil!");
                loadAvailableProjects();
                loadMyProjects();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Gagal mengambil project (mungkin sudah diambil).",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });




        btnRefreshAvail.addActionListener(e -> loadAvailableProjects());

        // ======================= MY PROJECTS PANEL ===================
        JPanel myPanel = new JPanel(new BorderLayout());
        myPanel.setBackground(new Color(30,20,50));

        myModel = new DefaultTableModel(
                new String[]{"Title","Company","Skill","Budget","Progress"}, 0
        );
        myTable = new JTable(myModel);
        styleTable(myTable);

        JScrollPane spMy = new JScrollPane(myTable);
        myPanel.add(spMy, BorderLayout.CENTER);

        JButton btnRefreshMy = new JButton("Refresh");
        styleButton(btnRefreshMy);
        JPanel myBtn = new JPanel();
        myBtn.setBackground(new Color(30,20,50));
        myBtn.add(btnRefreshMy);
        myPanel.add(myBtn, BorderLayout.SOUTH);

        btnRefreshMy.addActionListener(e -> loadMyProjects());

        // ====================== ADD PANELS TO CARD ==================
        content.add(profilePanel, "profile");
        content.add(availablePanel, "available");
        content.add(myPanel, "taken");

        CardLayout cl = (CardLayout) content.getLayout();

        btnProfile.addActionListener(e -> cl.show(content, "profile"));
        btnAvailable.addActionListener(e -> { loadAvailableProjects(); cl.show(content, "available"); });
        btnTaken.addActionListener(e -> { loadMyProjects(); cl.show(content, "taken"); });

        btnLogout.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        // initial data load
        loadAvailableProjects();
        loadMyProjects();
    }

    // ==================== BUTTON STYLE ==========================
    private JButton sidebarBtn(String text) {
        JButton b = new JButton(text);
        b.setBackground(new Color(90, 60, 150));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
        b.setFocusPainted(false);
        return b;
    }

    private void styleButton(JButton b) {
        b.setBackground(new Color(120, 60, 180));
        b.setForeground(Color.WHITE);
    }

    // ==================== TABLE STYLE ===========================
    private void styleTable(JTable table) {
        table.setBackground(new Color(45, 35, 80));
        table.setForeground(Color.WHITE);
        table.setRowHeight(28);
        table.setGridColor(Color.GRAY);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
    }

    // ==================== LOAD AVAILABLE ========================
    private void loadAvailableProjects() {
        availableModel.setRowCount(0);
        List<Project> list = db.getAvailableProjects();

        for (Project p : list) {
            availableModel.addRow(new Object[]{
                    p.getTitle(),
                    p.getCompanyName(),
                    p.getRequiredSkills(),
                    p.getBudget()
            });
        }
    }

    // ==================== LOAD MY PROJECTS ======================
    private void loadMyProjects() {
        myModel.setRowCount(0);
        List<Project> list = db.getProjectsByFreelancer(currentFreelancer.getId());

        for (Project p : list) {
            myModel.addRow(new Object[]{
                    p.getTitle(),
                    p.getCompanyName(),
                    p.getRequiredSkills(),
                    p.getBudget(),
                    p.getProgress()
            });
        }
    }
}
