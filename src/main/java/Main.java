import visualization.Visual;

import javax.swing.*;
import java.awt.*;

public class Main {

    //Запускаем
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Visual visual = new Visual();
        final JFrame window = new JFrame("Визуалицизация DFS и поиск цилка в графе");
        //Событие "закрыть" при нажатии по крестику окна
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Текстовое поле
        JTextField startNodeField = new JTextField();
        startNodeField.setBackground(Color.WHITE);
        startNodeField.setColumns(5);
        startNodeField.setText("start");

        JTextField textField1 = new JTextField();
        textField1.setBackground(Color.WHITE);
        textField1.setColumns(5);
        textField1.setText("from");

        JTextField textField2 = new JTextField();
        textField2.setBackground(Color.WHITE);
        textField2.setColumns(5);
        textField2.setText("to");

        JTextField addedEdges = new JTextField();
        addedEdges.setBackground(Color.WHITE);
        addedEdges.setColumns(30);
        addedEdges.setText("Добавленные дуги");

        //Создадим панель
        JPanel panel = new JPanel();

        //Создадим кнопки
        JButton startButton = new JButton("Начать визуализацию");
        JButton addEdgeButton = new JButton("Добавить ребро");

        //Событие для кнопки startButton
        startButton.addActionListener(e -> {
            window.setVisible(false);
            visual.setStartNode(startNodeField.getText());
            System.setProperty("org.graphstream.ui", "swing");
           visual.runVisual();
        });

        addEdgeButton.addActionListener(e -> {
            visual.addEdgeVisual(textField1.getText(), textField2.getText());
            if (addedEdges.getText().equals("") || addedEdges.getText().equals("Добавленные дуги")) {
                addedEdges.setText( textField1.getText() + " " + textField2.getText());
            } else {
                addedEdges.setText(addedEdges.getText() + ", " +
                        textField1.getText() + " " + textField2.getText());
            }
        });

        //Добавим кнопки и поля на панель
        panel.add(startNodeField);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(addEdgeButton);
        panel.add(startButton);
        panel.add(addedEdges);

        //Добавим панель в окно
        window.getContentPane().add(panel);

        window.pack();

        //Разместим программу по центру
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
