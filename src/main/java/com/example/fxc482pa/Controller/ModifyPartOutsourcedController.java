package com.example.fxc482pa.Controller;

import com.example.fxc482pa.Model.Implementation;
import com.example.fxc482pa.Model.InHouse;
import com.example.fxc482pa.Model.Inventory;
import com.example.fxc482pa.Model.Outsourced;
import com.example.fxc482pa.Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**This class is the controller for the "Modify Part Outsourced" view.*/
public class ModifyPartOutsourcedController implements Initializable {
    /** This stage variable allows the application to set the stages where the application is called upon. */
    Stage stage;

    /** This scene variable allows the application to get the location that is going to be set into the stage. */
    Parent scene;

    /**This text field variable is where the ID for the modified outsourced part is input by the user
     * and where the old ID is pre-set.*/
    @FXML
    private TextField modifyOutsourcedId;

    /**This text field variable is where the name for the modified outsourced part is input by the user
     * and where the old name is pre-set.*/
    @FXML
    private TextField modifyOutsourcedName;

    /**This text field variable is where the inventory for the modified outsourced part is input by the user
     * and where the old inventory is pre-set.*/
    @FXML
    private TextField modifyOutsourcedInv;

    /**This text field variable is where the price for the modified outsourced part is input by the user
     * and where the old price is pre-set.*/
    @FXML
    private TextField modifyOutsourcedPrice;

    /**This text field variable is where the maximum for the modified outsourced part is input by the user
     * and where the old maximum is pre-set.*/
    @FXML
    private TextField modifyOutsourcedMax;

    /**This text field variable is where the minimum for the modified outsourced part is input by the user
     * and where the old minimum is pre-set.*/
    @FXML
    private TextField modifyOutsourcedMin;

    /**This text field variable is where the company name for the modified outsourced part is input by the user
     * and where the old company name is pre-set.*/
    @FXML
    private TextField modifyOutsourcedCompanyName;

    /**This method returns user to the "Main Form" view.
     * @param event References event object created when the "Cancel" button is clicked. */
    @FXML
    public void openMainForm(ActionEvent event) throws IOException {
        Implementation.openNewScene(event,"/com/example/fxc482pa/MainForm.fxml");
    }

    /**Method that changes views from "Modify Part Outsourced" view to "Modify Part In House" view.
     * Sets data in current view text fields into the "Modify Part In House" view when radio button is clicked.
     * @param event References event object created when "Outsourced" radio button is clicked.*/
    @FXML
    public void openInHouse(ActionEvent event) throws IOException {
        int id = Integer.parseInt(modifyOutsourcedId.getText());
        String name = modifyOutsourcedName.getText();
        int stock = Integer.parseInt(modifyOutsourcedInv.getText());
        double price = Double.parseDouble(modifyOutsourcedPrice.getText());
        int min = Integer.parseInt(modifyOutsourcedMin.getText());
        int max = Integer.parseInt(modifyOutsourcedMax.getText());
        int machineId = 0;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/fxc482pa/ModifyPartInHouse.fxml"));
        loader.load();
        ModifyPartInHouseController MPOC = loader.getController();
        MPOC.sendPart((new InHouse(id,name,price,stock,min,max,machineId)));

        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Method saves the part that was modified.
     * Method takes all the user input provided in the text fields checking for errors, if an error occurs a displayed
     * messages is displayed instructing the user to correct them. If no errors occur the part is updated into inventory
     * and user is returned to the "Main Form" view.
     * @param event References event object created when the "Save" button is clicked. */
    @FXML
    public void modifyOutsourcedPart(ActionEvent event) throws IOException{
        try {
            int id = Integer.parseInt(modifyOutsourcedId.getText());
            String name = modifyOutsourcedName.getText();
            int stock = Integer.parseInt(modifyOutsourcedInv.getText());
            double price = Double.parseDouble(modifyOutsourcedPrice.getText());
            int max = Integer.parseInt(modifyOutsourcedMax.getText());
            int min = Integer.parseInt(modifyOutsourcedMin.getText());
            String companyName = modifyOutsourcedCompanyName.getText();

            int index = findIndex(new Outsourced(id,name,price,stock,min,max,companyName));


            if(name.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The name for the part was not set");
                return;
            }
            if(min >= max){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The minimum is greater than or equal to the maximum.");
                return;
            }
            if(stock > max || stock < min){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The inventory is not within the minimum and maximum range.");
                return;
            }
            if(companyName.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The company name was not set.");
                return;
            }

            Inventory.updatePart(index,new Outsourced(id,name,price,stock,min,max,companyName));

            Implementation.openNewScene(event,"/com/example/fxc482pa/MainForm.fxml");

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert,"Warning","Invalid or missing values were input.");
        }
    }

    //------------------------Implementation Detail-----------------------

    /**Method used by a controller to send the selected part into the current controller.
     * @param part Part object that is sent from another view.*/
    public void sendPart(Outsourced part){
        modifyOutsourcedId.setText(String.valueOf(part.getId()));
        modifyOutsourcedName.setText(part.getName());
        modifyOutsourcedInv.setText(String.valueOf(part.getStock()));
        modifyOutsourcedPrice.setText(String.valueOf(part.getPrice()));
        modifyOutsourcedMax.setText(String.valueOf(part.getMax()));
        modifyOutsourcedMin.setText(String.valueOf(part.getMin()));
        modifyOutsourcedCompanyName.setText(String.valueOf(part.getCompanyName()));
    }

    /**Method to find index of a part object.
     * @param part Part object to find the index of
     * @return Returns Index of part object or -1*/
    public int findIndex(Part part){
        int index = 0;
        for(var item : Inventory.getAllParts()){
            if(item.getId() == part.getId()){
                return index;
            }
            index++;
        }
        return -1;
    }

    //-------------------------------Initialize---------------------------------

    /**Method to initialize the "Modify Part In House" view and its components.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
