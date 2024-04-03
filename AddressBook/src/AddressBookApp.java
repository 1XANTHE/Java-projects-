
// Import necessary packages for Swing components and event handling
import javax.swing.*;
import java.awt.*;

// Define the main class for the Address Book application
public class AddressBookApp extends JFrame {
    // Declare variables for GUI components
    private JTextField n, p, e; // Text fields for name, phone, and email
    private DefaultListModel<String> m; // List model for holding contacts
    private JList<String> l; // List component to display contacts
    private java.util.List<C> c; // List to store contact objects

    // Constructor for initializing the Address Book application
    public AddressBookApp() {
        // Set window title, size, close operation, and position
        setTitle("Address Book");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Initialize text fields for name, phone, and email
        n = new JTextField();
        p = new JTextField();
        e = new JTextField();

        // Initialize "Add" button and attach ActionListener
        JButton a = new JButton("Add");
        a.addActionListener(e -> a()); // Lambda expression for button action

        // Initialize list model and list component
        m = new DefaultListModel<>();
        l = new JList<>(m);
        JScrollPane s = new JScrollPane(l); // Add list to scroll pane for scrolling

        // Initialize "Delete" button and attach ActionListener
        JButton d = new JButton("Delete");
        d.addActionListener(e -> d()); // Lambda expression for button action

        // Set layout of the frame
        setLayout(new BorderLayout());

        // Add panel for text fields to the top (north) of the frame
        add(new JPanel() {{
            setLayout(new GridLayout(3, 2)); // Use grid layout for arranging components
            // Add labels and text fields for name, phone, and email
            add(new JLabel("Name:"));
            add(n);
            add(new JLabel("Phone:"));
            add(p);
            add(new JLabel("Email:"));
            add(e);
        }}, BorderLayout.NORTH);

        // Add list scroll pane to the center of the frame
        add(s, BorderLayout.CENTER);

        // Add panel for buttons to the bottom (south) of the frame
        add(new JPanel() {{
            // Add "Add" and "Delete" buttons
            add(a);
            add(d);
        }}, BorderLayout.SOUTH);


        // Initialize the list of contacts
        c = new java.util.ArrayList<>();
    }

    // Method to handle adding a new contact
    private void a() {
        // Create a new contact object with the entered information and add it to the list
        c.add(new C(n.getText(), p.getText(), e.getText()));
        // Update the list to reflect the changes
        u();
        // Clear the text fields for entering new information
        n.setText("");
        p.setText("");
        e.setText("");
    }

    // Method to handle deleting a contact
    private void d() {
        // Get the index of the selected contact in the list
        int i = l.getSelectedIndex();
        // If a contact is selected (-1 means no contact selected), remove it from the list
        if (i != -1) {
            c.remove(i);
            // Update the list to reflect the changes
            u();
        }
    }

    // Method to update the list of contacts displayed in the GUI
    private void u() {
        // Clear the list model to prepare for updating
        m.clear();
        // Loop through the list of contacts and add each one to the list model
        for (C o : c) {
            m.addElement(o.toString());
        }
    }

    // Main method to start the Address Book application
    public static void main(String[] a) {
        // Run the application on the event dispatch thread to ensure proper Swing behavior
        SwingUtilities.invokeLater(() -> new AddressBookApp().setVisible(true));
    }

    // Inner class to represent a contact with name, phone, and email fields
    private static class C {
        private String n, p, e; // Fields for name, phone, and email

        // Constructor to initialize a contact object with the given information
        public C(String n, String p, String e) {
            this.n = n;
            this.p = p;
            this.e = e;
        }

        // Method to return a string representation of the contact

        @Override
        public String toString() {
            return n + " | " + p + " | " + e; // Format: Name | Phone | Email
        }
    }
}
