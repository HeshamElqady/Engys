package it.elqady.hesham.service;

import it.elqady.hesham.model.UserStats;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class FileProcessorApp {
    private static final UserParser USER_PARSER = new UserParser();

    public void start() {
        SwingUtilities.invokeLater(FileProcessorApp::createAndShowGUI);
    }

    public static void createAndShowGUI() {

        JFrame frame = new JFrame("File Processor Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        String[] columnNames = {"User", "Min", "Max", "Average"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        panel.add(tableScrollPane, BorderLayout.SOUTH);

        JButton openButton = getjButton(frame, textArea, tableModel);
        panel.add(openButton, BorderLayout.NORTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static JButton getjButton(JFrame frame, JTextArea textArea, DefaultTableModel tableModel) {
        JButton openButton = new JButton("Open File");
        openButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                processFile(file, textArea, tableModel);
            }
        });
        return openButton;
    }

    public static void processFile(File file, JTextArea textArea, DefaultTableModel tableModel) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            textArea.setText("");

            String line;
            while ((line = reader.readLine()) != null) {
                USER_PARSER.parseUser(line);
                textArea.append(line + "\n");
            }

            Map<String, UserStats> userStatsMap = FileService.getUserStatsMap(USER_PARSER.getUsers());
            tableModel.setRowCount(0);

            for (Map.Entry<String, UserStats> entry : userStatsMap.entrySet()) {
                UserStats stats = entry.getValue();
                tableModel.addRow(new Object[]{entry.getKey(), stats.getMin(), stats.getMax(), stats.getAverage()});
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}