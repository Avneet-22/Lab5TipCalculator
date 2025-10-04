package avneet.lab5tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {
    // formatters for currency and percentages
    private static final NumberFormat currency =
            NumberFormat.getCurrencyInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

    // GUI controls defined in FXML
    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    // called by FXMLLoader to initialize the controller
    public void initialize() {
        // 0-4 rounds down, 5-9 rounds up
        currency.setRoundingMode(RoundingMode.HALF_UP);

        // bind label text to slider value (like "15%")
        tipPercentageLabel.textProperty().bind(
                Bindings.format("%.0f%%", tipPercentageSlider.valueProperty())
        );

        // when slider changes, recalc
        tipPercentageSlider.valueProperty().addListener((obs, oldVal, newVal) -> calculate());

        // when text field changes, recalc
        amountTextField.textProperty().addListener((obs, oldVal, newVal) -> calculate());

        // do an initial calc
        calculate();
    }

    private void calculate() {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            tipPercentage = BigDecimal.valueOf(tipPercentageSlider.getValue() / 100.0);

            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        }
        catch (NumberFormatException ex) {
            tipTextField.setText(currency.format(0));
            totalTextField.setText(currency.format(0));
        }
    }
}