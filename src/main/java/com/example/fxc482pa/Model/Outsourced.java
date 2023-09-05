package com.example.fxc482pa.Model;

/**This class is for Outsourced Parts. It extends the Part class. */
public class Outsourced extends Part{
    /**Variable for the In House Parts machine ID. */
    private String companyName;

    /**Constructor for the Outsourced class.
     * @param id ID of part
     * @param name Name of part
     * @param price Price of part
     * @param stock Inventory of part
     * @param min Minimum of part
     * @param max Maximum of part
     * @param companyName Company name of part*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**Getter for the company name variable.*/
    public String getCompanyName() {
        return companyName;
    }

    /**Setter for the company name variable.*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
