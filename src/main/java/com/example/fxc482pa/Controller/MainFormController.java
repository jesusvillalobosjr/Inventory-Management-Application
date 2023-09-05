package com.example.fxc482pa.Controller;

import com.example.fxc482pa.Model.Implementation;
import com.example.fxc482pa.Model.InHouse;
import com.example.fxc482pa.Model.Inventory;
import com.example.fxc482pa.Model.Outsourced;
import com.example.fxc482pa.Model.Part;
import com.example.fxc482pa.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is the controller for the "Main Form" view. It contains all the methods and variables for the functionality
 * of the "Main Form" view.*/
public class MainFormController implements Initializable {
    /** This stage variable allows the application to set the stages where the application is called upon. */
    Stage stage;
    /** This scene variable allows the application to get the location that is going to be set into the stage. */
    Parent scene;

    /**This variable is used to manage the table view for all parts. */
    @FXML
    private TableView<Part> partTableView;

    /**This variable is used to set the ID of the part ID column in the parts table. */
    @FXML
    private TableColumn<Part,Integer> partIdCol;

    /**This variable is used to set the name of the part name column in the parts table. */
    @FXML
    private TableColumn<Part,String> partNameCol;

    /**This variable is used to set the inventory of the part inventory column in the parts table. */
    @FXML
    private TableColumn<Part,Integer> partInvCol;

    /**This variable is used to set the price of the part price column in the parts table. */
    @FXML
    private TableColumn<Part,Double> partPriceCol;

    /**This variable is used to manage the table view for all products*/
    @FXML
    private TableView<Product> productTableView;

    /**This variable is used to set the ID of the product ID column in the products table. */
    @FXML
    private TableColumn<Product,Integer> productIdCol;

    /**This variable is used to set the name of the product name column in the products table. */
    @FXML
    private TableColumn<Product,String> productNameCol;

    /**This variable is used to set the inventory of the product inventory column in the products table. */
    @FXML
    private TableColumn<Product,Integer> productInvCol;

    /**This variable is used to set the price of the product price column in the products table. */
    @FXML
    private TableColumn<Product,Double> productPriceCol;

    /**This text field variable is where the user can search for a part or parts. */
    @FXML
    private TextField partSearch;

    /**This text field variable is where the user can search for a product or products. */
    @FXML
    private TextField productSearch;

    /**This is the exit application method.
     * It exits the application when the "exit" button is clicked.
     * @param event References event object created when the "Exit" button is clicked.*/
    @FXML
    public void exitApplication(ActionEvent event){
        System.exit(0);
    }

    //------------------------Part-------------------------------

    /**This method opens the "Add Part" view.
     * It changes from the "Main Form" view to "Add In House Part" view.
     * @param event References event object that is created when Add button is clicked near the parts table. */
    @FXML
    public void openAddPart(ActionEvent event) throws IOException{
        Implementation.openNewScene(event,"/com/example/fxc482pa/AddPartInHouse.fxml");
    }

    /**This method searches for a part.
     * This method will search for a part either by name or ID which is input by the user.
     * If no part is found a message will be display stating so, if a part or parts are found a filtered table view
     * will be displayed.
     * @param event References event object created when the search button is clicked near the parts table. */
    @FXML
    public void searchForPart(ActionEvent event){
        String entryStr = partSearch.getText();
        if(searchPart(entryStr)){
            searchPart(entryStr);
            return;
        }
        try {
            int entry = Integer.parseInt(partSearch.getText());
            if(searchPart(entry)){
                searchPart(entry);
                return;
            }
        }catch (NumberFormatException e){}

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Implementation.showAlert(alert,"Part not found", "Part that was entered was not found");
    }

    /** This method opens the "Modify Parts" view.
     * Method gets selected part and will pass its data onto the "Modify Parts" view, if no item is selected
     * a warning will be displayed stating that a part needs to be selected. Method identifies if the part is
     * in house or outsourced to open correct view.
     * @param event References event object created when the Modify button is clicked near the parts table. */
    @FXML
    public void openModifyPart(ActionEvent event) throws IOException {
        var part = partTableView.getSelectionModel().getSelectedItem();
        if(!Implementation.partSelected(part)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert,"Warning","Part needs to be selected in order to modify");
            return;
        }

        if(part instanceof InHouse){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/fxc482pa/ModifyPartInHouse.fxml"));
            loader.load();
            ModifyPartInHouseController MPIHC = loader.getController();
            MPIHC.sendPart((InHouse) part);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/fxc482pa/ModifyPartOutsourced.fxml"));
            loader.load();
            ModifyPartOutsourcedController MPOC = loader.getController();
            MPOC.sendPart((Outsourced) part);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**This method deletes selected part from the parts table.
     * The part that is selected is chosen to be deleted, a message will be displayed asking to confirm or
     * deny the action that occurs upon clicking the "delete" button. If no part is selected a message will
     * be displayed instructing the user to do so.
     * @param event References the event object created when the Delete button is clicked near the parts table. */
    @FXML
    public void deleteSelectedPart(ActionEvent event){
        var partToBeDeleted = partTableView.getSelectionModel().getSelectedItem();
        if(!Implementation.partSelected(partToBeDeleted)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert, "Warning", "Part needs to be selected in order to be deleted.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete " + partToBeDeleted.getName() + " ?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            Inventory.deletePart(partToBeDeleted);
        }
    }

    //--------------------Products--------------------------------

    /**This method opens the "Add Product" view.
     * It changes from the "Main Form" view to "Add Product" view.
     * @param event References event object that is created when "add" button is clicked near the products table. */
    @FXML
    public void openAddProduct(ActionEvent event) throws IOException {
        Implementation.openNewScene(event,"/com/example/fxc482pa/AddProduct.fxml");
    }

    /** This method opens the "Modify Products" view.
     * Method gets the selected product and will pass its data onto the "Modify Products" view, if no product is selected
     * a warning will be displayed stating that a part needs to be selected. Method sends the products information into
     * the text fields and tables in the "Add Products" view.
     * @param event References event object created when the Modify button is clicked near the products table. */
    @FXML
    public void openModifyProduct(ActionEvent event) throws IOException {
        var product = productTableView.getSelectionModel().getSelectedItem();
        if(!productSelected(product)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert, "Warning","Product needs to be selected in order to modify.");
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/fxc482pa/ModifyProduct.fxml"));
        loader.load();
        ModifyProductController MPC = loader.getController();
        MPC.sendProduct(product);

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This method searches for a product.
     * This method will search for a product either by name or ID which is input by the user.
     * If no product is found a message will be displayed stating so, if a product or products are found it a filtered
     * table view is displayed.
     * @param event References event object created when the search button is clicked near the products table. */
    @FXML
    public void searchForProduct(ActionEvent event){
        String entryStr = productSearch.getText();
        if(searchProduct(entryStr)){
            searchProduct(entryStr);
            return;
        }
        try {
            int entry = Integer.parseInt(productSearch.getText());
            if(searchProduct(entry)){
                searchProduct(entry);
                return;
            }
        }catch (NumberFormatException e){}

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Implementation.showAlert(alert,"Product not found", "Product that was searched was not found");
    }

    /**This method deletes a product from the products table.
     * The product that is selected is chosen to be deleted, a message is displayed asking to confirm or deny the action that will occur
     * upon clicking the "delete" button. If no product is selected a message will be displayed instructing the user
     * to do so.
     * @param event References the event object created when the Delete button is clicked near the products table. */
    @FXML
    public void deleteSelectedProduct(ActionEvent event){
        var productToBeDeleted = productTableView.getSelectionModel().getSelectedItem();
        if(!productSelected(productToBeDeleted)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert, "Warning", "Product needs to be selected in order to be deleted.");
            return;
        }
        if(productHasAssociatedParts(productToBeDeleted)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Implementation.showAlert(alert, "Warning", "Product's associated parts need to be cleared before product can be deleted.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete " + productToBeDeleted.getName() + " ?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            Inventory.deleteProduct(productToBeDeleted);
        }
    }

    //-------------------Implementation Details-----------------

    /** This private method sets the part table view for a name search.
     * Method takes in a string that is input by the user to search for a part
     * by name setting the tableview to the filtered results.
     * @param name The name the user input to be searched for in the parts table.
     * @return Returns True or False. */
    private boolean searchPart(String name){
        ObservableList<Part> filtered = FXCollections.observableArrayList();
        boolean found = false;
        for(Part part : Inventory.getAllParts()){
            if((part.getName().toLowerCase()).contains(name.toLowerCase())){
                filtered.add(part);
                found = true;
            }
        }

        Implementation.setPartsTable(partTableView,partIdCol,partNameCol,partInvCol,partPriceCol,filtered);

        return found;
    }

    /** This private method sets the part table view for an ID search.
     * Method takes in an integer that is input by the user to search for a part
     * by ID setting the tableview to the filtered results.
     * @param id The part ID the user input to be searched for in the parts table.
     * @return Returns True or False. */
    private boolean searchPart(int id){
        ObservableList<Part> filtered = FXCollections.observableArrayList();
        boolean found = false;
        for(Part part : Inventory.getAllParts()){
            if(part.getId() == id){
                filtered.add(part);
                found = true;
            }
        }

        Implementation.setPartsTable(partTableView,partIdCol,partNameCol,partInvCol,partPriceCol,filtered);

        return found;
    }

    /** This private method sets the product table view for a name search.
     * Method takes in a string that is input by the user to search for a product
     * by name setting the tableview to the filtered results.
     * @param name The name the user input to be searched for in the products table.
     * @return Returns True or False. */
    private boolean searchProduct(String name){
        ObservableList<Product> filtered = FXCollections.observableArrayList();
        boolean found = false;
        for(Product product : Inventory.getAllProducts()){
            if((product.getName().toLowerCase()).contains(name.toLowerCase())){
                filtered.add(product);
                found = true;
            }
        }
        Implementation.setProductTable(productTableView,productIdCol,productNameCol,productInvCol,productPriceCol,filtered);
        return found;
    }

    /** This private method sets the product table view for an ID search.
     * Method takes in an integer that is input by the user to search for a part
     * by ID setting the tableview to the filtered results.
     * @param id The product ID the user input to be searched for in the parts table.
     * @return Returns True or False. */
    private boolean searchProduct(int id){
        ObservableList<Product> filtered = FXCollections.observableArrayList();
        boolean found = false;
        for(Product product : Inventory.getAllProducts()){
            if(product.getId() == id){
                filtered.add(product);
                found = true;
            }
        }

        Implementation.setProductTable(productTableView,productIdCol,productNameCol,productInvCol,productPriceCol,filtered);

        return found;
    }

    /**Method to determine whether a product was selected or not.
     * @param product variable instance of the class Product.
     * @return True or false */
    private boolean productSelected(Product product){
        return product != null;
    }

    /**Method to determine if a product has associated parts.
     * @param product varible instance of the class Product
     * @return True or False */
    private boolean productHasAssociatedParts(Product product){
        return  !product.getAllAssociatedParts().isEmpty();
    }

    //--------------------Initialize---------------------


    /**Method to initialize the "Main Form" view components.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Implementation.setPartsTable(partTableView,partIdCol,partNameCol,partInvCol,partPriceCol,Inventory.getAllParts());

        Implementation.setProductTable(productTableView,productIdCol,productNameCol,productInvCol,productPriceCol,Inventory.getAllProducts());
    }
}
