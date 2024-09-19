import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverterAWT extends Frame implements ActionListener {

    // GUI Components
    Choice baseCurrencyChoice, targetCurrencyChoice;
    TextField amountField;
    Label resultLabel, statusLabel;

    // Simulated Exchange Rates (Hardcoded)
    HashMap<String, Double> exchangeRates;

    // Constructor to build the UI and initialize data
    public CurrencyConverterAWT() {
        // Setup Frame Layout
        setLayout(new GridLayout(6, 2, 10, 10));
        setTitle("Currency Converter");
        setSize(400, 300);

        // Initialize hardcoded exchange rates
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD_EUR", 0.84);
        exchangeRates.put("EUR_USD", 1.19);
        exchangeRates.put("USD_INR", 74.50);
        exchangeRates.put("INR_USD", 0.013);
        exchangeRates.put("EUR_INR", 88.60);
        exchangeRates.put("INR_EUR", 0.0113);

        // Base Currency Selection
        add(new Label("Base Currency: "));
        baseCurrencyChoice = new Choice();
        baseCurrencyChoice.add("USD");
        baseCurrencyChoice.add("EUR");
        baseCurrencyChoice.add("INR");
        add(baseCurrencyChoice);

        // Target Currency Selection
        add(new Label("Target Currency: "));
        targetCurrencyChoice = new Choice();
        targetCurrencyChoice.add("USD");
        targetCurrencyChoice.add("EUR");
        targetCurrencyChoice.add("INR");
        add(targetCurrencyChoice);

        // Amount Input
        add(new Label("Amount: "));
        amountField = new TextField();
        add(amountField);

        // Convert Button
        Button convertButton = new Button("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

        // Label for Conversion Result
        resultLabel = new Label("");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(Color.BLUE);
        add(resultLabel);

        // Label for Error or Status Messages
        statusLabel = new Label("");
        statusLabel.setForeground(Color.RED);
        add(statusLabel);

        // Make the Frame visible
        setVisible(true);

        // Add Window Listener to handle close button action
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    // Action handler for Convert button
    @Override
    public void actionPerformed(ActionEvent e) {
        String baseCurrency = baseCurrencyChoice.getSelectedItem();
        String targetCurrency = targetCurrencyChoice.getSelectedItem();
        String amountStr = amountField.getText();

        // Input validation for amount and currency
        try {
            double amount = Double.parseDouble(amountStr);

            if (baseCurrency.equals(targetCurrency)) {
                statusLabel.setText("Base and target currencies must be different.");
                resultLabel.setText("");
                return;
            }

            // Perform currency conversion using simulated rates
            String conversionKey = baseCurrency + "_" + targetCurrency;
            if (exchangeRates.containsKey(conversionKey)) {
                double exchangeRate = exchangeRates.get(conversionKey);
                double convertedAmount = amount * exchangeRate;

                // Display the converted amount
                resultLabel.setText("Converted Amount: " + String.format("%.2f", convertedAmount) + " " + targetCurrency);
                statusLabel.setText("");
            } else {
                statusLabel.setText("Exchange rate not available.");
                resultLabel.setText("");
            }

        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid amount. Please enter a numeric value.");
        }
    }

    public static void main(String[] args) {
        new CurrencyConverterAWT();
    }
}
