

import javax.swing.*;
import java.awt.*;

public class ToDoListApp extends JFrame {
    // Declare necessary variables
    private DefaultListModel<String> listModel; // List model to hold tasks
    private JList<String> toDoList; // JList to display tasks
    private JTextField taskField; // JTextField for user input

    // Constructor to initialize the application
    public ToDoListApp() {
        // Set up the window title and size
        setTitle("ToDoList");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize listModel and toDoList
        listModel = new DefaultListModel<>();
        toDoList = new JList<>(listModel);

        // Initialize taskField and buttons
        taskField = new JTextField(20);
        JButton addButton = new JButton("Add");
        JButton completeButton = new JButton("Complete");
        JButton removeButton = new JButton("Remove");

        // Set up the layout of the application window
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Add a heading label at the top
        JLabel headingLabel = new JLabel("ToDoList");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(headingLabel, BorderLayout.NORTH);

        // Add the task list to the center of the window
        container.add(new JScrollPane(toDoList), BorderLayout.CENTER);

        // Set up the input panel at the bottom
        JPanel inputPanel = new JPanel();
        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(completeButton);
        inputPanel.add(removeButton);
        container.add(inputPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        addButton.addActionListener(e -> addTask());
        completeButton.addActionListener(e -> completeTask());
        removeButton.addActionListener(e -> removeTask());
    }

    // Method to add a task to the list
    private void addTask() {
        // Get the task text from the input field
        String task = taskField.getText().trim();
        // Check if the input is not empty
        if (!task.isEmpty()) {
            // Add the task to the list model and clear the input field

            listModel.addElement(task);
            taskField.setText("");
        }
    }

    // Method to mark a task as completed
    private void completeTask() {
        // Get the index of the selected task
        int selectedIndex = toDoList.getSelectedIndex();
        // Check if a task is selected
        if (selectedIndex != -1) {
            // Get the selected task and update its status
            String task = listModel.getElementAt(selectedIndex);
            listModel.setElementAt("[DONE] " + task, selectedIndex);
        }
    }

    // Method to remove a task from the list
    private void removeTask() {
        // Get the index of the selected task
        int selectedIndex = toDoList.getSelectedIndex();
        // Check if a task is selected

        if (selectedIndex != -1) {
            // Remove the selected task from the list model
            listModel.remove(selectedIndex);
        }
    }

    // Main method to create and display the application window
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListApp().setVisible(true));
    }
}
