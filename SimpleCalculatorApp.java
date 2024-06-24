import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculatorApp {

    private JFrame frame;
    private JPanel panel;
    private JTextField num1Field, num2Field, resultField;
    private JLabel resultLabel;

    public SimpleCalculatorApp() {
        frame = new JFrame("Simple Calculator");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        num1Field = new JTextField(10);
        num2Field = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        panel.add(new JLabel("Number 1:"));
        panel.add(num1Field);
        panel.add(new JLabel("Number 2:"));
        panel.add(num2Field);

        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");

        addButton.addActionListener(new OperatorListener('+'));
        subtractButton.addActionListener(new OperatorListener('-'));
        multiplyButton.addActionListener(new OperatorListener('*'));
        divideButton.addActionListener(new OperatorListener('/'));

        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);

        resultLabel = new JLabel("Result:");
        panel.add(resultLabel);
        panel.add(resultField);

        frame.add(panel);
        frame.setVisible(true);
    }

    private class OperatorListener implements ActionListener {
        private char operator;

        public OperatorListener(char operator) {
            this.operator = operator;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                double result = 0;

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            JOptionPane.showMessageDialog(frame, "Error: Division by zero");
                            return;
                        }
                        break;
                }

                resultField.setText(Double.toString(result));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error: Invalid number format");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleCalculatorApp();
            }
        });
    }
}

