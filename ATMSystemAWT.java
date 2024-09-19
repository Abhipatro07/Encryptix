import java.awt.*;
import java.awt.event.*;

// BankAccount class to handle user balance
class BankAccount {
    private double balance;

    // Constructor to initialize the account with an initial balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit an amount
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw an amount
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance to withdraw.");
        } else if (amount > 0) {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
        } else {
            System.out.println("Withdraw amount must be positive.");
        }
    }

    // Method to check the current balance
    public double checkBalance() {
        return balance;
    }
}

// ATM class with AWT GUI
class ATM extends Frame implements ActionListener {
    private BankAccount account;
    private TextField amountField;
    private Label balanceLabel, statusLabel;

    // Constructor to connect ATM to a specific BankAccount and build GUI
    public ATM(BankAccount account) {
        this.account = account;

        // Setting up the frame
        setLayout(new GridLayout(5, 2, 10, 10));

        // Balance Label
        balanceLabel = new Label("Balance: " + account.checkBalance());
        add(balanceLabel);
        add(new Label(""));  // Empty placeholder

        // Input for Amount
        add(new Label("Amount: "));
        amountField = new TextField();
        add(amountField);

        // Buttons for actions
        Button withdrawButton = new Button("Withdraw");
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        Button depositButton = new Button("Deposit");
        depositButton.addActionListener(this);
        add(depositButton);

        Button checkBalanceButton = new Button("Check Balance");
        checkBalanceButton.addActionListener(this);
        add(checkBalanceButton);

        Button exitButton = new Button("Exit");
        exitButton.addActionListener(this);
        add(exitButton);

        // Status Label for messages
        statusLabel = new Label("");
        statusLabel.setForeground(Color.RED);
        add(statusLabel);

        setTitle("ATM System");
        setSize(400, 300);
        setVisible(true);

        // Closing the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    // Action event handler for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            double amount = Double.parseDouble(amountField.getText());

            if (command.equals("Withdraw")) {
                if (amount <= account.checkBalance() && amount > 0) {
                    account.withdraw(amount);
                    statusLabel.setText("Withdrawn: " + amount);
                } else if (amount <= 0) {
                    statusLabel.setText("Amount must be positive.");
                } else {
                    statusLabel.setText("Insufficient balance.");
                }
            } else if (command.equals("Deposit")) {
                if (amount > 0) {
                    account.deposit(amount);
                    statusLabel.setText("Deposited: " + amount);
                } else {
                    statusLabel.setText("Amount must be positive.");
                }
            } else if (command.equals("Check Balance")) {
                statusLabel.setText("Current Balance: " + account.checkBalance());
            } else if (command.equals("Exit")) {
                dispose();
            }

            // Update the balance label
            balanceLabel.setText("Balance: " + account.checkBalance());
            amountField.setText("");

        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid amount. Please enter a number.");
        }
    }
}

// Main class to run the program
public class ATMSystemAWT {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of 1000
        BankAccount userAccount = new BankAccount(10000);

        // Create and display the ATM
        new ATM(userAccount);
    }
}
