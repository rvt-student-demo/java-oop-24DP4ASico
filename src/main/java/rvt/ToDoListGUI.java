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
        todoList = new ToDoList();  
        listModel = new DefaultListModel<>();

        
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
                todoList.add(task);          
                listModel.addElement(task);  
                taskField.setText("");
            }
        });

        removeButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0) {
                todoList.remove(index + 1);  
                listModel.remove(index);     
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
