package diceroller;

import java.net.URL;
import java.security.SecureRandom;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label result;

    @FXML
    private RadioButton advantage;

    @FXML
    private RadioButton disadvantage;

    @FXML
    private RadioButton normal;

    @FXML
    private TextArea historyBox;
    @FXML
    private Button rollButton;

    @FXML
    private ChoiceBox dtype;

    @FXML
    private ChoiceBox dMod;

    @FXML
    private ChoiceBox damount;

    SecureRandom rand = new SecureRandom();

    @FXML
    private void roll(ActionEvent event) {
        if (normal.isSelected()) {
            String maxString = dtype.getValue().toString();
            maxString = maxString.substring(1);
            int max = Integer.parseInt(maxString);
            max = max * Integer.parseInt(damount.getValue().toString());
            int resultint = rand.nextInt(max) + 1;
            int mod = Integer.parseInt(dMod.getValue().toString());
            result.setText(Integer.toString(resultint));
            historyBox.appendText(damount.getValue().toString()
                    + " X " + dtype.getValue().toString() + " = " + resultint + "\n");
            historyBox.appendText("Modifier = " + mod + "\n");
            int total = resultint + mod;
            historyBox.appendText("Total = " + total + "\n \n");

        } else if (advantage.isSelected()) {
            String maxString = dtype.getValue().toString();
            maxString = maxString.substring(1);
            int max = Integer.parseInt(maxString);
            max = max * Integer.parseInt(damount.getValue().toString());
            int mod = Integer.parseInt(dMod.getValue().toString());
            historyBox.appendText("Rolling with ADV! \n");
            historyBox.appendText("Modifier = " + mod + "\n");
            //first roll
            int resultint = rand.nextInt(max) + 1;
            historyBox.appendText(damount.getValue().toString()
                    + " X " + dtype.getValue().toString() + " = " + resultint + "\n");
            int total = resultint + mod;
            historyBox.appendText("Total = " + total + "\n");
            //second roll
            int resultint2 = rand.nextInt(max);
            historyBox.appendText(damount.getValue().toString()
                    + " X " + dtype.getValue().toString() + " = " + resultint2 + "\n");
            int total2 = resultint2 + mod;
            historyBox.appendText("Total = " + total2 + "\n");
            //report
            if (total > total2) {
                historyBox.appendText("First wins with: " + total + "\n \n");
            } else {
                historyBox.appendText("Second wins with: " + total2 + "\n \n");
            }
        } else if (disadvantage.isSelected()) {
            String maxString = dtype.getValue().toString();
            maxString = maxString.substring(1);
            int max = Integer.parseInt(maxString);
            max = max * Integer.parseInt(damount.getValue().toString());
            int mod = Integer.parseInt(dMod.getValue().toString());
            historyBox.appendText("Rolling with DADV! \n");
            historyBox.appendText("Modifier = " + mod + "\n");
            //first roll
            int resultint = rand.nextInt(max) + 1;
            historyBox.appendText(damount.getValue().toString()
                    + " X " + dtype.getValue().toString() + " = " + resultint + "\n");
            int total = resultint + mod;
            historyBox.appendText("Total = " + total + "\n");
            //second roll
            int resultint2 = rand.nextInt(max);
            historyBox.appendText(damount.getValue().toString()
                    + " X " + dtype.getValue().toString() + " = " + resultint2 + "\n");
            int total2 = resultint2 + mod;
            historyBox.appendText("Total = " + total2 + "\n");
            //report
            if (total < total2) {
                historyBox.appendText("First wins with: " + total + "\n \n");
            } else {
                historyBox.appendText("Second wins with: " + total2 + "\n \n");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dtype.getItems().add("D20");
        dtype.getItems().add("D12");
        dtype.getItems().add("D10");
        dtype.getItems().add("D8");
        dtype.getItems().add("D6");
        dtype.getItems().add("D4");
        dtype.getSelectionModel().selectFirst();

        damount.getItems().add("1");
        damount.getItems().add("2");
        damount.getItems().add("3");
        damount.getItems().add("4");
        damount.getItems().add("5");
        damount.getSelectionModel().selectFirst();

        for (int i = 0; i < 51; i++) {
            dMod.getItems().add(i);
        }
        dMod.getSelectionModel().selectFirst();
    }

}
