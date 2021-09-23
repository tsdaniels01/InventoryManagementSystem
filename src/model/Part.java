package model;

/**The Part class declares the variables and creates a constructor for the parts data. It is an abstract class
 * that cannot be instantiated. */

public abstract class Part {

    protected int partID; // Holds the part ID
    protected String partName; // Holds the part name
    protected double partPrice = 0.0; // Holds the part price
    protected int partInStock; // Holds the available inventory
    protected int min; // Holds the minimum inventory allowed
    protected int max; // Holds the maximum inventory allowed


    /**This method is a constructor for the Part class data. Because Part is abstract and never instantiated, the
     * constructor can only be called using the super method by the InHouse and Outsourced constructors.
     ********************************************************************************************************
     * LOGICAL ERROR
     * I did not initially create a Part constructor. The program would run, but the test data would not load.
     * After creating one and using it via the super command, the test data would load.
     ********************************************************************************************************
     * FUTURE ENHANCEMENT
     * Eliminating the abstract class and creating two overloaded constructors for the machineId and companyName would
     * eliminate the need for the InHouse and Outsourced models.
     * */

    public Part(int partID, String partName, double partPrice, int partInStock, int min, int max) {
        this.partID = partID; // Assigns the partID parameter to the protected class variable
        this.partName = partName; // Assigns the partName parameter to the protected class variable
        this.partPrice = partPrice; // Assigns the partPrice parameter to the protected class variable
        this.partInStock = partInStock; // Assigns the partInStock parameter to the protected class variable
        this.min = min; // Assigns the min parameter to the protected class variable
        this.max = max; // Assigns the max parameter to the protected class variable
    }

    /**This method is a getter for the PartID. It gets the partId when called.
     *
     * */
    public int getPartID() {
        return partID;
    }
    /**This method is a getter for the PartName. It gets the partName when called.
     *
     * */
    public String getPartName() {
        return partName;
    }
    /**This method is a getter for the PartPrice. It gets the partPrice when called
     *
     * */
    public double getPartPrice() {
        return partPrice;
    }
    /**This method is a getter for the PartInStock. It gets the inventory when called.
     *
     * */
    public int getPartInStock() {
        return partInStock;
    }
    /**This method is a getter for the Min. It gets the minimum inventory allowed when called.
     *
     * */

    public int getMin() {
        return min;
    }
    /**This method is a getter for the Max. It gets the maximum inventory allowed when called.
     *
     * */

    public int getMax() {
        return max;
    }

    /**This is a setter for the partID. It sets the partId when called.
     *
     * */
    public void setPartID(int partID) {
        this.partID = partID;
    }
    /**This is a setter for the partName. It sets the partName when called.
     *
     * */
    public void setPartName(String partName) {
        this.partName = partName;
    }
    /**This is a setter for the partPrice. It sets the partPrice when called.
     *
     * */
    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }
    /**This is a setter for the partInStock. It sets the inventory when called.
     *
     * */
    public void setPartInStock(int partInStock) {
        this.partInStock = partInStock;
    }
    /**This is a setter for the min. It sets the minimum inventory allowed when called.
     *
     * */
    public void setMin(int min) {
        this.min = min;
    }
    /**This is a setter for the max. It sets the maximum inventory allowed when called.
     *
     * */
    public void setMax(int max) {
        this.max = max;
    }


}

