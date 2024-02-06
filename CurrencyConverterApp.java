import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterApp {
    private JFrame frame;
    private JTextField amountTextField;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JLabel resultLabel;

    private Map<String, Double> exchangeRates;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new CurrencyConverterApp().initialize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initialize() {
        frame = new JFrame("Currency Converter");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(10, 20, 80, 20);
        frame.getContentPane().add(lblAmount);

        amountTextField = new JTextField();
        amountTextField.setBounds(100, 20, 100, 20);
        frame.getContentPane().add(amountTextField);

        JLabel lblFromCurrency = new JLabel("From Currency:");
        lblFromCurrency.setBounds(10, 60, 120, 20);
        frame.getContentPane().add(lblFromCurrency);

        fromCurrencyComboBox = new JComboBox<>();
        fromCurrencyComboBox.setBounds(140, 60, 100, 20);
        frame.getContentPane().add(fromCurrencyComboBox);

        JLabel lblToCurrency = new JLabel("To Currency:");
        lblToCurrency.setBounds(10, 100, 120, 20);
        frame.getContentPane().add(lblToCurrency);

        toCurrencyComboBox = new JComboBox<>();
        toCurrencyComboBox.setBounds(140, 100, 100, 20);
        frame.getContentPane().add(toCurrencyComboBox);

        JButton btnConvert = new JButton("Convert");
        btnConvert.setBounds(10, 140, 100, 25);
        frame.getContentPane().add(btnConvert);

        resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(10, 180, 300, 20);
        frame.getContentPane().add(resultLabel);

        // Populate currency combo boxes and exchange rates (dummy values for demonstration)
        populateCurrencies();
        populateExchangeRates();

        btnConvert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        frame.setVisible(true);
    }

    private void populateCurrencies() {
        // Populate combo boxes with currency codes (dummy values for demonstration)
        String[] currencies = {"USD", "EUR", "GBP", "JPY"};
        for (String currency : currencies) {
            fromCurrencyComboBox.addItem(currency);
            toCurrencyComboBox.addItem(currency);
        }
    }

    private void populateExchangeRates() {
        // Populate exchange rates (dummy values for demonstration)
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.73);
        exchangeRates.put("JPY", 110.0);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            String fromCurrency = fromCurrencyComboBox.getSelectedItem().toString();
            String toCurrency = toCurrencyComboBox.getSelectedItem().toString();

            double fromRate = exchangeRates.get(fromCurrency);
            double toRate = exchangeRates.get(toCurrency);

            double result = (amount / fromRate) * toRate;

            DecimalFormat df = new DecimalFormat("#.##");
            resultLabel.setText("Result: " + df.format(result) + " " + toCurrency);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }
}
