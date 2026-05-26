package rvt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoListGUI extends JFrame {

    private ToDoList todoList;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;

    public ToDoListGUI() {
        todoList = new ToDoList();  // <-- connection to your class
        listModel = new DefaultListModel<>();

        // Load tasks from file into GUI list
        for (String task : todoList.getTasks()) {
            listModel.addElement(task);
        }

        taskList = new JList<>(listModel);
        taskField = new JTextField(20);

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                todoList.add(task);          // <-- uses your logic
                listModel.addElement(task);  // <-- updates GUI
                taskField.setText("");
            }
        });

        removeButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0) {
                todoList.remove(index + 1);  // <-- uses your logic
                listModel.remove(index);     // <-- updates GUI
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(taskField);
        topPanel.add(addButton);
        topPanel.add(removeButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        setTitle("To‑Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListGUI::new);
    }
}
