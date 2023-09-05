package com.example.fxc482pa.Model;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

/**The implementation class is abstract and used to call functions that are called repeatedly across the application.*/
public abstract class Implementation {
    /** This stage variable allows the application to set the stages where the application is called upon. */
    private static Stage stage;

    /** This scene variable allows the application to get the location that is going to be set into the stage. */
    private static Parent scene;

    /**Method will open scene using the event and location arguments.
     * @param event References event object created by the source object.
     * @param location is the location where the scene will be set.*/
    public static void openNewScene(ActionEvent event, String location) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Implementation.class.getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Method will set an alert to the alert argument with the title and content arguments.
     * @param alert References the Alert instance that was submitted as an argument.
     * @param title The title for the alert to be shown.
     * @param content The content for the alert to be shown.*/
    public static void showAlert(Alert alert, String title, String content){
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**Method will open the "Add Outsourced Part" view via radio button.
     * @param event References event object created when radio button is clicked.
     * @param location Is the location where the scene will be set.*/
    public static void openOutsourced(ActionEvent event, String location) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Implementation.class.getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Method will open the "Add In House Part" view via radio button.
     * @param event References event object created when radio button is clicked.
     * @param location Is the location where the scene will be set.*/
    public static void openInHouse(ActionEvent event, String location) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Implementation.class.getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Method will set a table view with the table column arguments and the additional observable list argument.
     * @param partTableView Table view to be set.
     * @param partIdCol Part ID column to be set.
     * @param partNameCol Part name column to be set.
     * @param partInvCol Part inventory column to be set.
     * @param partPriceCol Part price column to be set.
     * @param observableList Observable list needed to set table view.*/
    public static void setPartsTable(TableView<Part> partTableView, TableColumn<Part,Integer> partIdCol, TableColumn<Part, String> partNameCol, TableColumn<Part,Integer> partInvCol, TableColumn<Part,Double> partPriceCol, ObservableList<Part> observableList){
        partTableView.setItems(observableList);
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**Method will set a table view with the table column arguments and the additional observable list argument.
     * @param productTableView Table view to be set.
     * @param productIdCol Product ID column to be set.
     * @param productNameCol Product name column to be set.
     * @param productInvCol Product inventory column to be set.
     * @param productPriceCol Product price column to be set.
     * @param observableList Observable list needed to set table view.*/
    public static void setProductTable(TableView<Product> productTableView, TableColumn<Product,Integer> productIdCol, TableColumn<Product, String> productNameCol, TableColumn<Product,Integer> productInvCol, TableColumn<Product,Double> productPriceCol, ObservableList<Product> observableList){
        productTableView.setItems(observableList);
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**Method that determines if a part is selected.
     * @param part Part instance to be checked.
     * @return True or False.*/
    public static boolean partSelected(Part part){
        return part != null;
    }
}
