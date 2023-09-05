package com.example.fxc482pa.Controller;

import com.example.fxc482pa.Model.Implementation;
import com.example.fxc482pa.Model.Inventory;
import com.example.fxc482pa.Model.Outsourced;
import com.example.fxc482pa.Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**This class is the controller for the "Add Part Outsourced" view. */
public class AddPartOutsourcedController implements Initializable {
    /** This text field variable is where the name for the new outsourced part is input by the user. */
    @FXML
    private TextField outsourcedPartName;

    /** This text field variable is where the stock for the new outsourced part is input by the user. */
    @FXML
    private TextField outsourcedPartInv;

    /** This text field variable is where the price for the new outsourced part is input by the user. */
    @FXML
    private TextField outsourcedPartPrice;

    /** This text field variable is where the maximum for the new outsourced part is input by the user. */
    @FXML
    private TextField outsourcedPartMax;

    /** This text field variable is where the minimum for the new outsourced part is input by the user. */
    @FXML
    private TextField outsourcedPartMin;

    /** This text field variable is where the company name for the new outsourced part is input by the user. */
    @FXML
    private TextField outsourcedPartCompanyName;

    /**Method returns user to the "Main Form" view
     * @param event References event object created when the "Cancel" button is clicked. */
    @FXML
    public void openMainForm(ActionEvent event) throws IOException {
        Implementation.openNewScene(event,"/com/example/fxc482pa/MainForm.fxml");
    }

    /**Method changes view to "Add Outsourced Part" view.
     * @param event References event object created when the "In House" radio button is selected. */
    @FXML
    public void openInHouse(ActionEvent event) throws IOException {
        Implementation.openInHouse(event,"/com/example/fxc482pa/AddPartInHouse.fxml");
    }

    /**Method saves the part that was created.
     * Method takes all the user input provided in the text fields checking for errors, if an error occurs a displayed
     * messages is displayed instructing the user to correct them. If no errors occur the part is added into inventory
     * and user is returned to the "Main Form" view.
     * @param event References event object created when the "Save" button is clicked. */
    @FXML
    public void saveOutsourcedPart(ActionEvent event) throws IOException {
        try{
            int id = Part.setPartID();
            String name = outsourcedPartName.getText();
            int stock = Integer.parseInt(outsourcedPartInv.getText());
            double price = Double.parseDouble(outsourcedPartPrice.getText());
            int max = Integer.parseInt(outsourcedPartMax.getText());
            int min = Integer.parseInt(outsourcedPartMin.getText());
            String companyName = outsourcedPartCompanyName.getText();

            if(name.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The name for the part was not set.");
                return;
            }
            if(min >= max){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The minimum is greater than or equal to the maximum");
                return;
            }
            if(stock > max || stock < min){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The inventory is not within the minimum or maximum range.");
                return;
            }
            if(companyName.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The company name is not set");
                return;
            }

            Inventory.addPart(new Outsourced(id,name,price,stock,min,max,companyName));

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert,"Warning","Invalid or missing values were input.");
            return;
        }

        Implementation.openNewScene(event,"/com/example/fxc482pa/MainForm.fxml");
    }

    //---------------------Initialize--------------------------------

    /**Method to initializes the "Save Part Outsourced." view components.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
