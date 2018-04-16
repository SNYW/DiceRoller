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
            roll();

        } else if (advantage.isSelected()) {
            historyBox.appendText("Rolling with ADV! \n");
            int firstroll = roll();
            int secondroll = roll();
            //reports
            if (firstroll > secondroll) {
                historyBox.appendText("First wins with: " + firstroll + "\n");
            } else {
                historyBox.appendText("Second wins with: " + secondroll + "\n");
            }
        } else if (disadvantage.isSelected()) {
            historyBox.appendText("Rolling with DADV! \n");
            int firstroll = roll();
            int secondroll = roll();
            //report
            if (firstroll < secondroll) {
                historyBox.appendText("First wins with: " + firstroll + "\n");
            } else {
                historyBox.appendText("Second wins with: " + secondroll + "\n");
            }
        }
        historyBox.appendText("------ \n");
    }

    public int roll() {
        int roll;
        // Pull info from UI
        String maxString = dtype.getValue().toString();
        String dAmount = damount.getValue().toString();
        //Clean maxString and make strings ints
        int mod = Integer.parseInt(dMod.getValue().toString());
        maxString = maxString.substring(1);
        int max = Integer.parseInt(maxString) * Integer.parseInt(dAmount);
        roll = rand.nextInt(max);
        //Append to report Box
        historyBox.appendText(dAmount + " X " + maxString + " = " + roll + "\n");
        historyBox.appendText("Modifier = " + mod + "\n");
        int total = roll + mod;
        historyBox.appendText("Total = " + total + "\n");
        return total;
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
