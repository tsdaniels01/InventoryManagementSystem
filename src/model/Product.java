package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**The Product class declares the variables and creates a constructor for the Product data.
 *
 * */

public class Product {

    // Holds the associated parts for the product
    private ObservableList<Part> allAssocParts = FXCollections.observableArrayList();
    protected int productID; // Holds the product ID
    protected String productName; // Holds the product name
    protected double productPrice = 0.0; // Holds the product price
    protected int productInStock; // Holds the available inventory
    protected int min; // Holds the minimum inventory allowed
    protected int max; // Holds the maximum inventory allowed

    /**This method is a constructor for the product data. It initializes a newly created Product object before
     * it is used.
     * */
    public Product(int productID, String productName, double productPrice, int productInStock, int min, int max) {
        this.productID = productID; // assigns the productID parameter to the protected variable
        this.productName = productName; // assigns the productName parameter to the protected variable
        this.productPrice = productPrice; // assigns the productPrice parameter to the protected variable
        this.productInStock = productInStock; // assigns the productInStock parameter to the protected variable
        this.min = min; // assigns the min parameter to the protected variable
        this.max = max; // assigns the max parameter to the protected variable
    }

    /**This method is an overloaded Product constructor, which includes the associated parts list.
     *************************************************************************************************************
     * LOGICAL ERROR
     * I did not understand how I was supposed to connect the assocPartsList with the Product without making it static.
     * There was no reference to an overloaded constructor on the UML Diagram. I reached out to Live Instructor support
     * for help on this one. After being informed that this was acceptable, I created the overloaded constructor.
     *************************************************** **********************************************************
     * FUTURE ENHANCEMENT
     * Since all Products are required to have an associated part according to the specs, there is no reason for two
     * constructors.
     * */
    public Product(int productID, String productName, double productPrice, int productInStock, int min, int max,
                   ObservableList assocPartsList) {
        this.allAssocParts = assocPartsList; // Assigns the assocPartsList parameter to the private class ObservableList
        this.productID = productID; // Assigns the productID parameter to the protected variable
        this.productName = productName; // Assigns the productName parameter to the protected variable
        this.productPrice = productPrice; // Assigns the productPrice parameter to the protected variable
        this.productInStock = productInStock; // Assigns the productInStock parameter to the protected variable
        this.min = min; // Assigns the min parameter to the protected variable
        this.max = max; // Assigns the max parameter to the protected variable
    }

    /**This is a getter for the allAssocParts. It returns the assocParts when called*/
    public ObservableList<Part> getAllAssocParts() {
        return allAssocParts;
    }

    /**This is a setter for the allAssocParts list. It sets the allAssocParts list if called*/
    public void setAllAssocParts(ObservableList<Part> allAssocParts) {
        this.allAssocParts = allAssocParts;
    }
    /**This is a getter for the productID. It returns the productID when called*/
    public int getProductID() {
        return productID;
    }
    /**This is a setter for the productID. It sets the productId when called.*/
    public void setProductID(int productID) {
        this.productID = productID;
    }
    /**This is a getter for the productName. It returns the productName when called*/
    public String getProductName() {
        return productName;
    }
    /**This is a setter for the productName. It sets the productName when called.*/
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**This is a getter for the productPrice. It returns the productPrice when called*/
    public double getProductPrice() {
        return productPrice;
    }
    /**This is a setter for the productPrice. It sets the productPrice when called.*/
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    /**This is a getter for the productInStock. It returns the productInStock when called*/
    public int getProductInStock() {
        return productInStock;
    }
    /**This is a setter for the productInStock. It sets the productInStock when called.*/
    public void setProductInStock(int productInStock) {
        this.productInStock = productInStock;
    }
    /**This is a getter for the min. It returns the minimum inventory allowed when called*/
    public int getMin() {
        return min;
    }
    /**This is a setter for the min. It sets the minimum inventory allowed when called.*/
    public void setMin(int min) {
        this.min = min;
    }
    /**This is a getter for the max. It returns the maximum inventory allowed when called*/
    public int getMax() {
        return max;
    }
    /**This is a setter for the max. It sets the maximum inventory allowed when called.*/
    public void setMax(int max) {
        this.max = max;
    }
    /**
     *This method adds a part to the allAssocParts list
     * @param part allows a part from the Part class to be appended.
     * */
    public void addAssocPart(Part part) {allAssocParts.add(part);}

    /**
     * This method deletes a part from the allAssocParts list
     * @param selectedAssociatedPart this parameter removes the selectedAssociatedPart
     * */
    public boolean deleteAssocPart (Part selectedAssociatedPart) {

           return getAllAssocParts().remove(selectedAssociatedPart);
        }

}



