import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int targetNumber;
    private JTextField guessField;
    private JTextArea feedbackArea;
    private JButton submitButton;
    private JButton resetButton;
    private int attempts;

    public NumberGuessingGame() {
        // Set the title and layout
        setTitle("Number Guessing Game");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Initialize components
        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);
        submitButton = new JButton("Submit Guess");
        resetButton = new JButton("Reset Game");
        feedbackArea = new JTextArea(5, 20);
        feedbackArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(feedbackArea);

        // Add components to frame
        add(instructionLabel);
        add(guessField);
        add(submitButton);
        add(resetButton);
        add(scrollPane);

        // Generate the random number between 1 and 100
        generateRandomNumber();

        // Set button listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guessText = guessField.getText();
                try {
                    int userGuess = Integer.parseInt(guessText);
                    processGuess(userGuess);
                } catch (NumberFormatException ex) {
                    feedbackArea.setText("Please enter a valid number.");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRandomNumber();
                feedbackArea.setText("Game reset! Guess a number between 1 and 100.");
                guessField.setText("");
            }
        });

        // Make the GUI visible
        setVisible(true);
    }

    // Generate a random number between 1 and 100
    private void generateRandomNumber() {
        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1;
        attempts = 0;
    }

    // Process the user's guess
    private void processGuess(int userGuess) {
        attempts++;
        if (userGuess < targetNumber) {
            feedbackArea.setText("Too low! Try again.\nAttempts: " + attempts);
        } else if (userGuess > targetNumber) {
            feedbackArea.setText("Too high! Try again.\nAttempts: " + attempts);
        } else {
            feedbackArea.setText("Congratulations! You guessed the number in " + attempts + " attempts.\nClick 'Reset Game' to play again.");
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGame();
    }
}
