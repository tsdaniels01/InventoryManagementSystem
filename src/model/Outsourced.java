package model;

/**
 * The Outsourced class is inherited from the Part class and implements the companyName to differentiate between
 * the InHouse parts.
 * */

public class Outsourced extends Part{

    private String companyName; // Holds the companyName

    /**This method is the getter for the companyName. It returns the companyName if called.
     *
     * */
    public String getCompanyName() { return companyName;}

    /**This method is the setter for the companyName. It sets the companyName if called.
     *
     * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**This method is a constructor for the Outsourced part. This method inherits the abstract part constructor
     * including the additional companyName parameter. It initializes a newly created Outsourced object before it is
     * used.
     * @param companyName Is appended to the abstract Part constructor creating a new overloaded constructor.
     **************************************************************************************************************
     * LOGICAL ERROR
     * At first I wasn't sure if I had to create a constructor for the abstract parts class. I tried to run the program
     * without a parts constructor but the test data would not load in the partsTableView. I realized that the parts
     * constructor could be inherited by the Outsourced constructor utilizing the super method.
     **************************************************************************************************************
     * FUTURE ENHANCEMENT
     * Add a associated partId to the constructor or add an overloaded constructor to connect the part to the Products
     * that they are associated with.
     */
        public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
            super(id, name, price, stock, min, max);

            this.companyName = companyName; // assigns the constructor parameter to the private companyName variable.

    }

}
