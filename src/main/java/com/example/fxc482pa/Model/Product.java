package com.example.fxc482pa.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**Class for all product objects.*/
public class Product {
    /**Observable list variable to store a product objects associated parts.*/
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /** Part ID variable for all products.*/
    private static int productIds = 1;

    /**Variable for the products ID. */
    private int id;

    /**Variable for the products name. */
    private String name;

    /**Variable for the products price. */
    private double price;

    /**Variable for the products stock. */
    private int stock;

    /**Variable for the products minimum. */
    private int min;

    /**Variable for the products maximum. */
    private int max;

    /**Constructor for the Product class.
     * @param id ID of product
     * @param name Name of product
     * @param price Price of product
     * @param stock Inventory of product
     * @param min Minimum of product
     * @param max Maximum of product*/
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Getter for the ID variable.
     *@return Returns ID of product */
    public int getId() {
        return id;
    }

    /**Setter for the ID variable
     * @param id ID value for the product.*/
    public void setId(int id) {
        this.id = id;
    }

    /**Getter for the name variable
     * @return Returns name of product.*/
    public String getName() {
        return name;
    }

    /**Setter for the name variable
     * @param name Name value for the product.*/
    public void setName(String name) {
        this.name = name;
    }

    /**Getter for the price variable
     * @return Returns price of the product.*/
    public double getPrice() {
        return price;
    }

    /**Setter for the price variable
     * @param price Price value for the product.*/
    public void setPrice(double price) {
        this.price = price;
    }

    /**Getter for the stock variable
     * @return Returns stock value for the product.*/
    public int getStock() {
        return stock;
    }

    /**Setter for the stock variable
     * @param stock Stock value for the product.*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**Getter for the minimum variable
     * @return Returns minimum value for the product.*/
    public int getMin() {
        return min;
    }

    /**Setter for the minimum variable
     * @param min Minimum value for the product.*/
    public void setMin(int min) {
        this.min = min;
    }

    /**Getter for the maximum variable
     * @return Returns Maximum value for the product.*/
    public int getMax() {
        return max;
    }

    /**Setter for the maximum variable
     * @param max Maximum value for the product.*/
    public void setMax(int max) {
        this.max = max;
    }

    /**Method to add a part object into the products associated parts.
     * @param part Part object to be added into a products associated parts. */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**Method to delete a part object from the products associated parts.
     * @param selectedAssociatedPart Part object to be deleted from an products associated parts. */
    public void deleteAssociatedPart(Part selectedAssociatedPart){
        associatedParts.remove(selectedAssociatedPart);
    }

    /**Method to get all associated parts from an object.
     * @return Observable list of all associated parts of an object.*/
    public ObservableList<Part> getAllAssociatedParts(){
        return  associatedParts;
    }

    /**Setter for the product ID variable
     * @return Returns the value of ID plus one when called.*/
    public static int getProductId(){
        return productIds++;
    }
}
