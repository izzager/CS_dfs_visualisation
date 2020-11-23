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
        final JFrame window = new JFrame("Визуалицизация DFS и поиск цикла в графе");
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

        //Создадим панель
        JPanel panel = new JPanel();

        //Создадим кнопки
        JButton startButton = new JButton("Начать визуализацию");
        JButton addEdgeButton = new JButton("Добавить дугу");

        System.setProperty("org.graphstream.ui", "swing");
        visual.getGraph().display();
        //Событие для кнопки startButton
        startButton.addActionListener(e -> {
            window.setVisible(false);
            visual.setStartNode(startNodeField.getText());
            visual.runVisual();
        });

        addEdgeButton.addActionListener(e -> {
            visual.addEdgeVisual(textField1.getText(), textField2.getText());
            visual.makeLabels(visual.getGraph());
        });

        //Добавим кнопки и поля на панель
        panel.add(startNodeField);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(addEdgeButton);
        panel.add(startButton);

        //Добавим панель в окно
        window.getContentPane().add(panel);

        window.pack();

        //Разместим программу по центру
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
