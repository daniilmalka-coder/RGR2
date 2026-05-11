package main;

import core.*;
import components.*;
import xml.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class App extends JFrame {
    private Repository repo = new Repository();
    private TableUI tableUI;
    private GraphUI graphUI;

    public App() {
        setTitle("Приклад JavaBeans");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableUI = new TableUI();
        graphUI = new GraphUI();

        tableUI.getLogic().setRepo(repo);
        graphUI.setRepo(repo);

        tableUI.getLogic().addListener(e -> graphUI.repaint());

        add(tableUI, BorderLayout.WEST);
        add(graphUI, BorderLayout.CENTER);

        // Нижня панель з кнопками
        JPanel bottomPanel = new JPanel();
        JButton openBtn = new JButton("Відкрити");
        JButton saveBtn = new JButton("Зберегти");
        JButton clearBtn = new JButton("Очистити");
        JButton exitBtn = new JButton("Завершити");

        // ВІДКРИТИ XML
        openBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                repo = XMLReader.read(file.getAbsolutePath()); // Читаємо файл

                // Оновлюємо компоненти новими даними
                tableUI.getLogic().setRepo(repo);
                graphUI.setRepo(repo);
                JOptionPane.showMessageDialog(this, "Дані завантажено!");
            }
        });

        // ЗБЕРЕГТИ XML
        saveBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                String path = file.getAbsolutePath();
                if (!path.toLowerCase().endsWith(".xml")) path += ".xml";

                XMLWriter.save(repo, path); // Зберігаємо файл
                JOptionPane.showMessageDialog(this, "Файл збережено успішно!");
            }
        });

        // ОЧИСТИТИ
        clearBtn.addActionListener(e -> {
            repo = new Repository(); // Створюємо новий порожній список
            tableUI.getLogic().setRepo(repo);
            graphUI.setRepo(repo);
        });

        // ЗАВЕРШИТИ
        exitBtn.addActionListener(e -> System.exit(0));

        bottomPanel.add(openBtn);
        bottomPanel.add(saveBtn);
        bottomPanel.add(clearBtn);
        bottomPanel.add(exitBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}