

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;

    public Calculator() {
        setTitle("My Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Text field to display input and result
        display = new JTextField();
        display.setEditable(false);
        display.setPreferredSize(new Dimension(300, 50)); // Increase display field size
        display.setFont(new Font("Arial", Font.PLAIN, 24)); // Enlarge font size
        add(display, BorderLayout.NORTH);

        // Panel to hold buttons
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 4));

        // Buttons for digits and operators
        String[] buttonLabels = {
                "1", "2", "3", "=",
                "4", "5", "6", "+",
                "7", "8", "9", "-",
                "0", "*", "/", "C" // Changed "Backspace" to "C"
        };

        // Creating buttons and adding action listener
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setPreferredSize(new Dimension(75, 75)); // Enlarge button size
            button.setFont(new Font("Arial", Font.PLAIN, 20)); // Enlarge font size
            buttonsPanel.add(button);
        }

        // Adding buttons panel to the frame
        add(buttonsPanel, BorderLayout.CENTER);
    }


    // Action performed when a button is clicked
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("=".equals(command)) {
            // Perform calculation when "=" button is clicked
            try {
                String expression = display.getText();
                double result = evaluateExpression(expression);
                // Format result to two decimal places
                DecimalFormat df = new DecimalFormat("#.##");
                display.setText(df.format(result));
            } catch (NumberFormatException | ArithmeticException ex) {
                display.setText("Error");
            }
        } else if ("C".equals(command)) {
            // Remove the last character when "C" button is clicked
            String currentText = display.getText();
            if (currentText.length() > 0) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else {
            // Update display with the clicked button's label
            display.setText(display.getText() + command);
        }
    }

    // Evaluate the expression entered by the user
    private double evaluateExpression(String expression) {
        // Implementing simple evaluation logic
        // For a beginner, let's just handle basic arithmetic operations

        // Splitting expression into numbers and operator
        String[] parts = expression.split("[+\\-*/]");
        double num1 = Double.parseDouble(parts[0]);
        double num2 = Double.parseDouble(parts[1]);
        char operator = expression.charAt(parts[0].length());

        // Performing arithmetic operation based on the operator
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    // Main method to start the calculator application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator().setVisible(true);
            }
        });
    }
}
