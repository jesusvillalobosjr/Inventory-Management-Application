package com.example.fxc482pa.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**Class where all the Inventory is stored.*/
public class Inventory {
    /**Observable list variable where all parts are stored.*/
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**Observable list variable where all products are stored.*/
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**Method to add a part into inventory.
     * @param newPart Part object to be added into inventory.*/
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**Method to add a product into inventory.
     * @param newProduct Product object to be added into inventory.*/
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**Method to return a part searched by ID.
     * @param partId ID of the part being searched.
     * @return Part object or null.*/
    public static Part lookUpPart(int partId){
        for(var part : allParts){
            if(part.getId() == partId)
                return part;
        }
        return null;
    }

    /**Method to return a product searched by ID.
     * @param productId ID of the part being searched.
     * @return Product object or null.*/
    public static Product lookUpProduct(int productId){
        for(var product : allProducts){
            if(product.getId() == productId)
                return product;
        }
        return null;
    }

    /**Method to return a part searched by name.
     * @param partName Name of the part being searched.
     * @return Part object or null.*/
    public static Part lookUpPart(String partName){
        for(var part : allParts){
            if(part.getName() == partName)
                return part;
        }
        return null;
    }

    /**Method to return a product searched by name.
     * @param productName Name of the product being searched.
     * @return Product object or null.*/
    public static Product lookUpProduct(String productName){
        for(var product : allProducts){
            if(product.getName() == productName)
                return product;
        }
        return null;
    }

    /**Method to update a part in inventory.
     * @param newPart Updated part object.
     * @param index index of part to be updated.*/
    public static void updatePart(int index, Part newPart){
        allParts.set(index,newPart);
    }

    /**Method to update a product in inventory.
     * @param newProduct Updated product object.
     * @param index index of product to be updated.*/
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index,newProduct);
    }

    /**Method to delete a part from inventory
     * @param selectedPart Part object to be deleted
     * @return True or False.*/
    public static boolean deletePart(Part selectedPart){
        if(allParts.remove(selectedPart))
            return true;
        return false;
    }

    /**Method to delete a product from inventory
     * @param selectedProduct Product object to be deleted
     * @return True or False.*/
    public static boolean deleteProduct(Product selectedProduct){
        if(allProducts.remove(selectedProduct))
            return true;
        return false;
    }

    /**Method to get all parts observable list.
     * @return Observable list of all parts in inventory.*/
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**Method to get all products observable list.
     * @return Observable list of all products in inventory.*/
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
