package ui;

import database.DatabaseManager;
import models.Project;
import models.Freelancer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployerDashboard extends JFrame {

    private DatabaseManager db;
    private CardLayout card;
    private JPanel contentPanel;

    private DefaultTableModel projectTableModel;
    private JTable projectTable;

    public EmployerDashboard(DatabaseManager db) {
        this.db = db;

        setTitle("Employer / HRD Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(new Color(40, 30, 70));
        sidebar.setLayout(new GridLayout(10, 1, 0, 10));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel lblTitle = new JLabel("HRD PANEL", SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        sidebar.add(lblTitle);

        JButton btnCreate = sidebarButton("➕  Buat Proyek");
        JButton btnMyProjects = sidebarButton("📁  Proyek Saya");
        JButton btnLogout = sidebarButton("🚪  Logout");

        sidebar.add(btnCreate);
        sidebar.add(btnMyProjects);
        sidebar.add(new JLabel(""));
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        card = new CardLayout();
        contentPanel = new JPanel(card);
        contentPanel.setBackground(new Color(30, 20, 50));
        add(contentPanel, BorderLayout.CENTER);

        contentPanel.add(createCreateProjectPanel(), "CREATE");
        contentPanel.add(createMyProjectsPanel(), "MINE");

        btnCreate.addActionListener(e -> card.show(contentPanel, "CREATE"));
        btnMyProjects.addActionListener(e -> {
            loadProjects();
            card.show(contentPanel, "MINE");
        });

        btnLogout.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }

    private JButton sidebarButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(new Color(90, 60, 150));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
        return b;
    }

    private JPanel createCreateProjectPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(35, 25, 60));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("📝 Buat Proyek Baru", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        panel.add(title, gbc);
        gbc.gridwidth=1;

        JLabel lblTitle = new JLabel("Judul Proyek:");
        lblTitle.setForeground(Color.WHITE);
        JTextField txtTitle = new JTextField();

        gbc.gridy=1; gbc.gridx=0;
        panel.add(lblTitle, gbc);
        gbc.gridx=1;
        panel.add(txtTitle, gbc);

        JLabel lblCompany = new JLabel("Nama Perusahaan:");
        lblCompany.setForeground(Color.WHITE);
        JTextField txtCompany = new JTextField();

        gbc.gridy=2; gbc.gridx=0;
        panel.add(lblCompany, gbc);
        gbc.gridx=1;
        panel.add(txtCompany, gbc);

        JLabel lblSkill = new JLabel("Skill Dibutuhkan:");
        lblSkill.setForeground(Color.WHITE);
        JTextField txtSkill = new JTextField();

        gbc.gridy=3; gbc.gridx=0;
        panel.add(lblSkill, gbc);
        gbc.gridx=1;
        panel.add(txtSkill, gbc);

        JLabel lblBudget = new JLabel("Budget (Rp):");
        lblBudget.setForeground(Color.WHITE);
        JTextField txtBudget = new JTextField();

        gbc.gridy=4; gbc.gridx=0;
        panel.add(lblBudget, gbc);
        gbc.gridx=1;
        panel.add(txtBudget, gbc);

        JLabel lblDesc = new JLabel("Deskripsi:");
        lblDesc.setForeground(Color.WHITE);
        JTextArea txtDesc = new JTextArea(4,20);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);

        JScrollPane sp = new JScrollPane(txtDesc);

        gbc.gridy=5; gbc.gridx=0;
        panel.add(lblDesc, gbc);
        gbc.gridx=1;
        panel.add(sp, gbc);

        JButton btnSubmit = new JButton("✔ SIMPAN");
        btnSubmit.setBackground(new Color(120, 60, 180));
        btnSubmit.setForeground(Color.WHITE);

        gbc.gridy=6; gbc.gridx=0; gbc.gridwidth=2;
        panel.add(btnSubmit, gbc);

        btnSubmit.addActionListener(e -> {
            try {
                String t = txtTitle.getText().trim();
                String c = txtCompany.getText().trim();
                String s = txtSkill.getText().trim();
                double b = Double.parseDouble(txtBudget.getText().trim());
                String d = txtDesc.getText().trim();

                if (t.isEmpty() || c.isEmpty() || s.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Field tidak boleh kosong!");
                    return;
                }

                Project pObj = new Project(t, c, d, s, b);
                db.insertProject(pObj);

                JOptionPane.showMessageDialog(this, "Proyek berhasil dibuat!");
                txtTitle.setText("");
                txtCompany.setText("");
                txtSkill.setText("");
                txtBudget.setText("");
                txtDesc.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Data tidak valid!");
            }
        });

        return panel;
    }

    private JPanel createMyProjectsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(35, 25, 60));

        JLabel title = new JLabel("📁 Proyek Perusahaan", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);

        projectTableModel = new DefaultTableModel(new String[]{"Judul", "Skill", "Budget", "Progress"}, 0);
        projectTable = new JTable(projectTableModel);
        projectTable.setBackground(new Color(45, 35, 80));
        projectTable.setForeground(Color.WHITE);
        projectTable.setRowHeight(28);

        JScrollPane sp = new JScrollPane(projectTable);
        sp.getViewport().setBackground(new Color(45, 35, 80));
        panel.add(sp, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(new Color(35, 25, 60));

        JButton btnDelete = new JButton("🗑 Delete");
        JButton btnRefresh = new JButton("🔄 Refresh");

        btnDelete.setBackground(new Color(150, 50, 80));
        btnDelete.setForeground(Color.WHITE);

        btnRefresh.setBackground(new Color(90, 50, 160));
        btnRefresh.setForeground(Color.WHITE);

        btnPanel.add(btnDelete);
        btnPanel.add(btnRefresh);
        panel.add(btnPanel, BorderLayout.SOUTH);

        btnDelete.addActionListener(e -> {
            int row = projectTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Pilih proyek dulu!");
                return;
            }
            String titleProj = (String) projectTableModel.getValueAt(row, 0);
            Project target = db.findProjectByTitle(titleProj);
            if (target == null) {
                JOptionPane.showMessageDialog(this, "Proyek tidak ditemukan!");
                return;
            }
            int choose = JOptionPane.showConfirmDialog(this, "Hapus proyek?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choose == JOptionPane.YES_OPTION) {
                db.deleteProject(target.getId());
                loadProjects();
            }
        });

        btnRefresh.addActionListener(e -> loadProjects());

        return panel;
    }

    private void loadProjects() {
        List<Project> list = db.getAllProjects();
        projectTableModel.setRowCount(0);
        for (Project p : list) {
            projectTableModel.addRow(new Object[]{
                    p.getTitle(), p.getRequiredSkills(), p.getBudget(), p.getProgress()
            });
        }
    }
}
