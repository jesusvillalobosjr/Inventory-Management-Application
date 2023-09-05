package com.example.fxc482pa.Controller;

import com.example.fxc482pa.Model.Implementation;
import com.example.fxc482pa.Model.Inventory;
import com.example.fxc482pa.Model.Part;
import com.example.fxc482pa.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**This class is the controller for the "Modify Product" view.*/
public class ModifyProductController implements Initializable {
    /**This text field variable is where the ID for the modified product is input by the user
     * and where the old ID is pre-set.*/
    @FXML
    private TextField modifyProductId;

    /**This text field variable is where the name for the modified product is input by the user
     * and where the old name is pre-set.*/
    @FXML
    private TextField modifyProductName;

    /**This text field variable is where the inventory for the modified product is input by the user
     * and where the old inventory is pre-set.*/
    @FXML
    private TextField modifyProductInv;

    /**This text field variable is where the price for the modified product is input by the user
     * and where the old price is pre-set.*/
    @FXML
    private TextField modifyProductPrice;

    /**This text field variable is where the maximum for the modified product is input by the user
     * and where the old maximum is pre-set.*/
    @FXML
    private TextField modifyProductMax;

    /**This text field variable is where the minimum for the modified product is input by the user
     * and where the old minimum is pre-set.*/
    @FXML
    private TextField modifyProductMin;

    /**This table view variable is used to manage the table view for all parts. */
    @FXML
    private TableView<Part> allPartsTable;

    /**This table column variable is used to set the ID of the part ID column in the parts table. */
    @FXML
    private TableColumn<Part,Integer> partIdCol;

    /**This table column variable is used to set the name of the part name column in the parts table. */
    @FXML
    private TableColumn<Part,String> partNameCol;

    /**This table column variable is used to set the inventory of the part inventory column in the parts table. */
    @FXML
    private TableColumn<Part,Integer> partInvCol;

    /**This table column variable is used to set the price of the part price column in the parts table. */
    @FXML
    private TableColumn<Part,Double> partPriceCol;

    /**This table view variable is used to manage the table view for the products associated parts. */
    @FXML
    private TableView<Part> associatedPartTableView;

    /**This table column variable is used to set and hold the ID of the part ID column in the associated parts table. */
    @FXML
    private TableColumn<Part,Integer> associatedPartId;

    /**This table column variable is used to set and hold the name of the part name column in the associated parts table. */
    @FXML
    private TableColumn<Part,String> associatedPartName;

    /**This table column variable is used to set and hold the inventory of the part inventory column in the associated parts table. */
    @FXML
    private TableColumn<Part,Integer> associatedPartInv;

    /**This table column variable is used to set and hold the price of the part price column in the parts table. */
    @FXML
    private TableColumn<Part,Double> associatedPartPrice;

    /**This text field variable is where the user can search for a part or parts. */
    @FXML
    private TextField searchParts;

    /**This observable list is used to set and hold the products associated parts. */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**Method returns user to the "Main Form" view
     * @param event References event object created when the "Cancel" button is clicked. */
    @FXML
    public void openMainForm(ActionEvent event) throws IOException {
        Implementation.openNewScene(event,"/com/example/fxc482pa/MainForm.fxml");
    }

    /**This method searches for a part.
     * This method will search for a part either by name or ID which is input by the user.
     * If no part is found a message will be display stating so, if a part or parts are found a filtered table view
     * will be displayed.
     * @param event References event object created when the search button is clicked near the parts table. */
    @FXML
    public void searchFromAllParts(ActionEvent event){
        String entryStr = searchParts.getText();
        if(search(entryStr)){
            search(entryStr);
            return;
        }
        try {
            int entry = Integer.parseInt(searchParts.getText());
            if(search(entry)){
                search(entry);
                return;
            }
        }catch (NumberFormatException e){}

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Implementation.showAlert(alert,"Information","Part not found");
    }

    /**This method adds selected parts from all parts table to the associated parts table.
     * The part selected will be added and displayed in the associated parts table. If no part
     * selected a message instructing the user to do so will be displayed.
     * @param event References event object created when the "Add" button is clicked. */
    @FXML
    public void addAssociatedPart(ActionEvent event){
        var selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if(!Implementation.partSelected(selectedPart)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert,"Warning","Part was not selected to move into associated parts.");
            return;
        }
        associatedParts.add(selectedPart);
    }

    /**This method deletes selected part from the associated parts table.
     * The part that is selected is chosen to be deleted, a message will be displayed asking to confirm or
     * deny the action that occurs upon clicking the "Remove Associated Part" button. If no part is selected a message will
     * be displayed instructing the user to do so.
     * @param event References the event object created when the Delete button is clicked near the parts table. */
    @FXML
    public void removeAssociatedPart(ActionEvent event){
        var selectedPart = associatedPartTableView.getSelectionModel().getSelectedItem();
        if(!Implementation.partSelected(selectedPart)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert,"Warning","Part was not selected to be removed from the associated part table.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete " + selectedPart.getName() + " ?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            associatedParts.remove(selectedPart);
        }
    }

    /**Method saves the product that was modified with its associated parts.
     * Method takes all the user input provided in the text fields checking for errors. If an error occurs, a
     * message is displayed instructing the user to correct them. If no errors occur the product is updated into inventory
     * and user is returned to the "Main Form" view.
     * @param event References event object created when the "Save" button is clicked. */
    @FXML
    public void saveProduct(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(modifyProductId.getText());
            String name = modifyProductName.getText();
            double price = Double.parseDouble(modifyProductPrice.getText());
            int stock = Integer.parseInt(modifyProductInv.getText());
            int min = Integer.parseInt(modifyProductMin.getText());
            int max = Integer.parseInt(modifyProductMax.getText());

            if(name.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The name for the part was not set.");
                return;
            }
            if (min >= max) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The minimum is greater than or equal to the maximum.");
                return;
            }
            if(stock > max || stock < min){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Implementation.showAlert(alert,"Warning","The inventory is not within the minimum and maximum range.");
                return;
            }

            var product = new Product(id, name, price, stock, min, max);
            int index = findIndex(product);
            for (var associatedPart : associatedParts) {
                product.addAssociatedPart(associatedPart);
            }
            Inventory.updateProduct(index,product);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert,"Warning","Invalid or missing values were input.");
            return;
        }

        Implementation.openNewScene(event,"/com/example/fxc482pa/MainForm.fxml");
    }

    //------------------------Implementation Detail------------------------------------
    /**Method used by a controller to send the selected product into the current controller.
     * @param product Product object that is sent from another view.*/
    public void sendProduct(Product product){
        modifyProductId.setText(String.valueOf(product.getId()));
        modifyProductName.setText(product.getName());
        modifyProductInv.setText(String.valueOf(product.getStock()));
        modifyProductPrice.setText(String.valueOf(product.getPrice()));
        modifyProductMax.setText(String.valueOf(product.getMax()));
        modifyProductMin.setText(String.valueOf(product.getMin()));

        associatedParts = product.getAllAssociatedParts();

        Implementation.setPartsTable(associatedPartTableView,associatedPartId,associatedPartName,associatedPartInv,associatedPartPrice,associatedParts);
    }

    /**Method to find index of a product object.
     * @param product Product object to find the index of
     * @return Returns Index of product object or -1*/
    public int findIndex(Product product){
        int index = 0;
        for(var productInInventory : Inventory.getAllProducts()){
            if(product.getId() == productInInventory.getId()){
                return index;
            }
            index++;
        }
        return -1;
    }

    /** This private method sets the part table view for a name search.
     * Method takes in a string that is input by the user to search for a part
     * by name setting the tableview to the filtered results.
     * @param name The name the user input to be searched for in the parts table.
     * @return Returns True or False. */
    public boolean search(String name){
        ObservableList<Part> filtered = FXCollections.observableArrayList();
        boolean found = false;
        for(Part part : Inventory.getAllParts()){
            if((part.getName().toLowerCase()).contains(name.toLowerCase())){
                filtered.add(part);
                found = true;
            }
        }
        Implementation.setPartsTable(allPartsTable,partIdCol,partNameCol,partInvCol,partPriceCol,filtered);
        return found;
    }

    /** This private method sets the part table view for an ID search.
     * Method takes in an integer that is input by the user to search for a part
     * by ID setting the tableview to the filtered results.
     * @param id The part ID the user input to be searched for in the parts table.
     * @return Returns True or False. */
    public boolean search(int id){
        ObservableList<Part> filtered = FXCollections.observableArrayList();
        boolean found = false;
        for(Part part : Inventory.getAllParts()){
            if(part.getId() == id){
                filtered.add(part);
                found = true;
            }
        }

        Implementation.setPartsTable(allPartsTable,partIdCol,partNameCol,partInvCol,partPriceCol,filtered);

        return found;
    }

    //------------------------------Initialize---------------------------------

    /**Method to initialize the "Modify Product" view components.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Implementation.setPartsTable(allPartsTable,partIdCol,partNameCol,partInvCol,partPriceCol, Inventory.getAllParts());
    }
}
