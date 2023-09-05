package com.example.fxc482pa.Model;

/**Abstract class to be inherited from for all parts.*/
public abstract class Part {
    /** Part ID variable for all parts.*/
    private static int partIDs = 1;

    /**Variable for the parts ID. */
    private int id;

    /**Variable for the parts name. */
    private String name;

    /**Variable for the parts price. */
    private double price;

    /**Variable for the parts stock. */
    private int stock;

    /**Variable for the parts minimum. */
    private int min;

    /**Variable for the parts maximum. */
    private int max;

    /**Constructor for the Part class.
     * @param id ID of part
     * @param name Name of part
     * @param price Price of part
     * @param stock Inventory of part
     * @param min Minimum of part
     * @param max Maximum of part*/
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Getter for the ID variable.
     *@return Returns ID of part */
    public int getId() {
        return id;
    }

    /**Setter for the ID variable
     * @param id ID value for the part.*/
    public void setId(int id) {
        this.id = id;
    }

    /**Getter for the name variable
     * @return Returns name of part.*/
    public String getName() {
        return name;
    }

    /**Setter for the name variable
     * @param name Name value for the part.*/
    public void setName(String name) {
        this.name = name;
    }

    /**Getter for the price variable
     * @return Returns price of the part.*/
    public double getPrice() {
        return price;
    }

    /**Setter for the price variable
     * @param price Price value for the part.*/
    public void setPrice(double price) {
        this.price = price;
    }

    /**Getter for the stock variable
     * @return Returns stock value for the part.*/
    public int getStock() {
        return stock;
    }

    /**Setter for the stock variable
     * @param stock Stock value for the part.*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**Getter for the minimum variable
     * @return Returns minimum value for the part.*/
    public int getMin() {
        return min;
    }

    /**Setter for the minimum variable
     * @param min Minimum value for the part.*/
    public void setMin(int min) {
        this.min = min;
    }

    /**Getter for the maximum variable
     * @return Returns Maximum value for the part.*/
    public int getMax() {
        return max;
    }

    /**Setter for the maximum variable
     * @param max Maximum value for the part.*/
    public void setMax(int max) {
        this.max = max;
    }

    /**Setter for the part ID variable
     * @return Returns the value of ID plus one when called.*/
    public static int setPartID(){
        return partIDs++;
    }
}
