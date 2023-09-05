package com.example.fxc482pa.Controller;

import com.example.fxc482pa.Model.Implementation;
import com.example.fxc482pa.Model.InHouse;
import com.example.fxc482pa.Model.Inventory;
import com.example.fxc482pa.Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**This class is the controller for the "Add Part In House" view. Contains all variables and methods for the
 * functionality of the "Add Part In House" view.*/
public class AddPartInHouseController implements Initializable {
    /**This text field variable is where the name for the new in house part is input by the user*/
    @FXML
    private TextField inHouseNameTxt;

    /**This text field variable is where the stock for the new in house part is input by the user*/
    @FXML
    private TextField inHouseInvTxt;

    /**This text field variable is where the price for the new in house part is input by the user*/
    @FXML
    private TextField inHousePriceTxt;

    /**This text field variable is where the maximum for the new in house part is input by the user*/
    @FXML
    private TextField inHouseMaxTxt;

    /**This text field variable is where the minimum for the new in house part is input by the user*/
    @FXML
    private TextField inHouseMinTxt;

    /**This text field variable is where the machine ID for the new in house part is input by the user*/
    @FXML
    private TextField inHouseMachineIdTxt;

    /**This method returns user to the "Main Form" view.
     * @param event References event object created when the "Cancel" button is clicked. */
    @FXML
    public void openMainForm(ActionEvent event) throws IOException {
        Implementation.openNewScene(event,"/com/example/fxc482pa/MainForm.fxml");
    }

    /**Method changes view to the "Add Part Outsourced" view.
     * @param event References event object created when the "Outsourced" radio button is selected. */
    @FXML
    public void openOutsourced(ActionEvent event) throws IOException {
        Implementation.openOutsourced(event,"/com/example/fxc482pa/AddPartOutsourced.fxml");
    }

    /**Method saves the part that was created.
     * Method takes all the user input provided in the text fields checking for errors, if an error occurs a displayed
     * messages is displayed instructing the user to correct them. If no errors occur the part is added into inventory
     * and user is returned to the "Main Form" view.
     * @param event References event object created when the "Save" button is clicked. */
    @FXML
    public void saveInHousePart(ActionEvent event) throws IOException {
        try{
            String name = inHouseNameTxt.getText();
            int stock = Integer.parseInt(inHouseInvTxt.getText());
            double price = Double.parseDouble(inHousePriceTxt.getText());
            int max = Integer.parseInt(inHouseMaxTxt.getText());
            int min = Integer.parseInt(inHouseMinTxt.getText());
            int machineId = Integer.parseInt(inHouseMachineIdTxt.getText());

            if(name.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The name for the part was not set");
                return;
            }
            if(min >= max){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The minimum is greater than or equal to the maximum");
                return;
            }
            if(stock > max || stock < min){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The inventory is not within the minimum and maximum range.");
                return;
            }

            Inventory.addPart(new InHouse(Part.setPartID(),name,price,stock,min,max,machineId));
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert,"Warning","Invalid or missing values were input.");
            return;
        }

        Implementation.openNewScene(event,"/com/example/fxc482pa/MainForm.fxml");
    }

    //-------------------------Initialize-------------------------------

    /**Method to initializes the "Save Part In House" view components.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
