package model;


/**
 * The InHouse class is inherited from the Part class and implements the machineID to differentiate between
 * the Outsourced parts.
 * */

public class InHouse extends Part {

    private int machineID; // Holds the machineID

    /**
     * This method is the getter for the machineId. It returns the machineID if called.
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * This method is the setter for the machineId. It sets the machineID if called.
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    /**
     * This method is a constructor for the InHouse part. This method inherits the abstract part constructor including
     * the additional machineId parameter. It initializes a newly created InHouse object before it is used.
     *
     * @param machineID Is appended to the abstract Part constructor creating a new overloaded constructor.
     *
     **************************************************************************************************************
     * LOGICAL ERROR
     * At first I wasn't sure if I had to create a constructor for the abstract parts class. I tried to run the program
     * without a parts constructor but the test data would not load in the partsTableView. I realized that the parts
     * constructor could be inherited by the InHouse constructor utilizing the super method.
     * *************************************************************************************************************
     * FUTURE ENHANCEMENT
     * Add a associated partId to the constructor or add an overloaded constructor to connect the part to the Products
     * that they are associated with.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);// calling abstract Part constructor

        this.machineID = machineID; // assigning constructor parameter to the private machineID.
    }

}