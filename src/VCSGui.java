import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class VCSGui extends JFrame {
    private JTextField fileField, commitField, hashField;
    private JTextArea consoleArea;
    private DefaultListModel<String> workspaceModel, objectsModel;

    public VCSGui() {
        setTitle("AutoRABIT Special - MyGit DevSecOps Dashboard");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- 1. CONTROL PANEL (TOP) ---
        JPanel controlPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        controlPanel.setBorder(BorderFactory.createTitledBorder("VCS Execution Engine"));

        controlPanel.add(new JLabel(" File Name:"));
        fileField = new JTextField("sample.txt");
        controlPanel.add(fileField);
        JButton btnAdd = new JButton("Stage File (add)");
        controlPanel.add(btnAdd);

        controlPanel.add(new JLabel(" Commit Message:"));
        commitField = new JTextField("Feature update");
        controlPanel.add(commitField);
        JButton btnCommit = new JButton("Commit Snapshot");
        controlPanel.add(btnCommit);

        controlPanel.add(new JLabel(" Rollback Hash:"));
        hashField = new JTextField("");
        controlPanel.add(hashField);
        JButton btnRollback = new JButton("Restore State (rollback)");
        controlPanel.add(btnRollback);

        add(controlPanel, BorderLayout.NORTH);

        // --- 2. VISUALIZER PANEL (MIDDLE) ---
        JPanel visualizerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        
        workspaceModel = new DefaultListModel<>();
        JList<String> workspaceList = new JList<>(workspaceModel);
        JPanel p1 = new JPanel(new BorderLayout());
        p1.setBorder(BorderFactory.createTitledBorder("Current Workspace Directory"));
        p1.add(new JScrollPane(workspaceList), BorderLayout.CENTER);

        objectsModel = new DefaultListModel<>();
        JList<String> objectsList = new JList<>(objectsModel);
        JPanel p2 = new JPanel(new BorderLayout());
        p2.setBorder(BorderFactory.createTitledBorder(".mygit/objects (Secure Vault)"));
        p2.add(new JScrollPane(objectsList), BorderLayout.CENTER);

        visualizerPanel.add(p1);
        visualizerPanel.add(p2);
        add(visualizerPanel, BorderLayout.CENTER);

        // --- 3. CONSOLE LOGS (BOTTOM) ---
        consoleArea = new JTextArea(8, 20);
        consoleArea.setEditable(false);
        consoleArea.setBackground(Color.BLACK);
        consoleArea.setForeground(Color.GREEN);
        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        JPanel consolePanel = new JPanel(new BorderLayout());
        consolePanel.setBorder(BorderFactory.createTitledBorder("VCS Live Output Logs"));
        consolePanel.add(new JScrollPane(consoleArea), BorderLayout.CENTER);
        
        add(consolePanel, BorderLayout.SOUTH);

        // --- EVENT ACTIONS ---
        btnAdd.addActionListener(e -> {
            StagingArea.add(fileField.getText());
            logToConsole("Staged file: " + fileField.getText());
            refreshDirectoryViews();
        });

        btnCommit.addActionListener(e -> {
            CommitEngine.commit(commitField.getText());
            logToConsole("Executed commit: " + commitField.getText());
            refreshDirectoryViews();
        });

        btnRollback.addActionListener(e -> {
            RollbackEngine.rollback(hashField.getText(), fileField.getText());
            logToConsole("Attempted Rollback using Hash: " + hashField.getText());
            refreshDirectoryViews();
        });

        // Initial view load
        Repository.init();
        refreshDirectoryViews();
        logToConsole("System Initialized. Hidden storage directory (.mygit) loaded successfully.");
    }

    private void logToConsole(String message) {
        consoleArea.append(">> " + message + "\n");
    }

    private void refreshDirectoryViews() {
        workspaceModel.clear();
        objectsModel.clear();

        // Scan Current Workspace
        File currentDir = new File(".");
        File[] files = currentDir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) workspaceModel.addElement(f.getName());
            }
        }

        // Scan Secure Vault (.mygit/objects)
        File objectsDir = new File(".mygit/objects");
        File[] snapshots = objectsDir.listFiles();
        if (snapshots != null) {
            for (File s : snapshots) {
                if (s.isFile()) objectsModel.addElement(s.getName());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VCSGui().setVisible(true));
    }

    private static class RollbackEngine {
        static void rollback(String hash, String file) {
            // Placeholder fallback for missing rollback engine class
            System.err.println("RollbackEngine fallback invoked for hash: " + hash + ", file: " + file);
        }
    }
}