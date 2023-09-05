package com.example.fxc482pa.Model;

/**This class is for In House Parts. It extends the Part class. */
public class InHouse extends Part {
    /**Variable for the In House Parts machine ID. */
    private int machineId;

    /**Constructor for the In House class.
     * @param id ID of part
     * @param name Name of part
     * @param price Price of part
     * @param stock Inventory of part
     * @param min Minimum of part
     * @param max Maximum of part
     * @param machineId Machine ID of part*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**Getter for the machine ID variable.*/
    public int getMachineId() {
        return machineId;
    }

    /**Setter for the machine ID variable.*/
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
