package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;


/**
 * This class provides the data and static methods for the program. It provides both data for Parts and Product as
 * well as methods for adjusting the data as needed.
 ****************************************************************************************************************
 * FUTURE ENHANCEMENT
 * After realizing that a lot of code was used several times throughout the application, I think that it would be
 * more efficient to create more methods in the Inventory model that are accessible throughout the application,
 * especially with the FXML loaders and Alert messages.
 ****************************************************************************************************************
 * */
public class Inventory {
    // Holds a list of Parts
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    // Holds a list of Filtered Parts for search engine
    private static ObservableList<Part> allFilteredParts = FXCollections.observableArrayList();
    // Holds a list of Products
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    // Holds a list of Filtered Products for the search engine
    private static ObservableList<Product> allFilteredProducts = FXCollections.observableArrayList();

    /**This method adds a part to the allParts list*/
    public static void addPart(Part part) {
        allParts.add(part);
    }
    /**This method adds a product to the allProduct list*/
    public static void addProduct(Product product) {
        allProducts.add(product);
    }
    /**This method adds a part to the filteredPart list*/
    public static void addFilteredPart(Part part) {
        allFilteredParts.add(part);
    }
    /**This method retrieves allParts from a list*/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    /**This method retrieves allProducts from a list*/
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    /**This method retrieves allFilteredParts from a list*/
    public static ObservableList<Part> getAllFilteredParts() {
        return allFilteredParts;
    }
    /**This method retrieves allFilterdProducts from a list*/
    public static ObservableList<Product> getAllFilteredProducts() {
        return allFilteredProducts;
    }

    /**This method is used to search for a part by ID
     * @param id is used to search allParts by Integer ID
     * @return the partID that is found or null if not found
     * */
    public static Part lookupPart(int id) {

        for (Part part : Inventory.getAllParts()) { // Returns allParts to be searched
            if (part.getPartID() == id) // Finds part by ID
                return part; // Returns the part

        }
        return null;
    }
    /**
     * This method searches the allParts list for a part by the partName
     * @param name is to look up a part by the String name
     * @return either allParts or the filteredParts found
     * */
    public static ObservableList<Part> lookupPart(String name) {

        if (!(getAllFilteredParts()).isEmpty()) // verifying the list is not empty
            getAllFilteredParts().clear();

        for (Part inHouse : getAllParts()) { // Returns allParts to be searched
            if (inHouse.getPartName().contains(name)) // Searching by Name
                getAllFilteredParts().add(inHouse); // Add to list

        }

        if (getAllFilteredParts().isEmpty()) // Returned if not found
            return getAllParts();
        else
            return getAllFilteredParts(); // Returned if found

    }
    /**This method is used to search for a product by ID
     * @param id is used to search allProducts by Integer ID
     * @return the productID that is found or null if not found
     * */
    public static Product lookupProduct(int id) {

        for (Product product : Inventory.getAllProducts()) { // Returns allParts to be searched
            if (product.getProductID() == id) // Finds product by ID
                return product; // Returns the product

        }
        return null;
    }
    /** This method searches the allProducts list for a product by the productName
     * @param name is to look up a product by the String name
     * @return either allProducts or the filteredProducts found
     * */
    public static ObservableList<Product> lookupProduct(String name) {

        if (!(getAllFilteredProducts()).isEmpty()) // verifying the list is not empty
            getAllFilteredProducts().clear();

        for (Product product : getAllProducts()) { // Returns allProducts to be searched
            if (product.getProductName().contains(name)) // Searching by Product Name
                getAllFilteredProducts().add(product); // Adds Product to list

        }

        if (getAllFilteredProducts().isEmpty()) // Returns if not found
            return getAllProducts();
        else
            return getAllFilteredProducts(); // Returns if found
    }
    /**
     * This method updates a preexisting part by partID
     * @param  index used to search for parts based on the Integer index
     * @param  selectedPart replaces part with newPart
     *
     */
    public static void updatePart(int index, Part selectedPart) {

        int i = -1;
        for (Part part : Inventory.getAllParts()) {
            i++;
            if (part.getPartID() == index) {// Search by ID
                getAllParts().set(i, selectedPart);

            }

        }
    }
    /**
     * This method updates a preexisting product by productID
     * @param  index used to search for Product based on the Integer index
     * @param  newProduct replaces product with newProduct
     */

    public static void updateProduct(int index, Product newProduct) {

        int i = -1; //
        for (Product product : getAllProducts()) { // Returns allProducts to be searched
            i++;

            if (product.getProductID() == index) { // Finds product by ID
                getAllProducts().set(i, newProduct); // Updates product with newProduct
            }
        }

    }
    /**
     * This method deletes a part that is selected
     * @param selectedPart is used to delete the selected part
     * @return a boolean value based on whether the part was removed or not
     * */
    public static boolean deletePart(Part selectedPart){

        return getAllParts().remove(selectedPart);
    }
    /**
     * This method deletes a product that is selected
     * @param selectedProduct is used to delete the selected product
     * @return a boolean value based on whether the product was removed or not
     * */
    public static boolean deleteProduct(Product selectedProduct) {

        return getAllProducts().remove(selectedProduct);

    }
    /**
     * This method searches the allParts list for a part by the partID
     * @param id is to look up a part by the integer ID
     * @return a boolean value based on the search
     * */
    public static boolean selectPart(int id) {
        for (Part part : Inventory.getAllParts()) { // Returns allParts to be searched
            if (part.getPartID() == id) // Search by ID
                return true;

        }
        return false;
    }
    /**
     * This method searches the allProducts list for a part by the productID
     * @param id is to look up a product by the integer ID
     * @return a boolean value based on the search
     * */
    public static boolean selectProduct(int id) {
        for (Product product : getAllProducts()) { // Returns allProducts to be searched
            if (product.getProductID() == id) // Searches Products by ID
                return true;

        }
        return false;
    }
    /**This method returns a random number for the InHouse ID.
     * @return a random number that is not assigned to another number
     ********************************************************************************************************
     * LOGICAL ERROR
     * I could not find a way to set boundaries for the random number (e.g. min and max). I was able to research
     * examples over the internet until I found one that worked. After using the lookupPart method to ensure
     * that the same ID's weren't assigned, I was still seeing the same ID's. By switching the logic to not true and
     * reversing the return methods, I was able to get it to work.
     ********************************************************************************************************
     * */

    public static int inHouseID() {
        int number; // Holds the number
        int min = 10; // Sets the minimum to 10
        int max = 29; // Sets the maximum to 29
        Random rand = new Random(); // Calls for a new random number
        number = rand.nextInt(max - min) + min; // assigns boundaries and random number to number

        if (selectPart(number) != true) // Verifies that the random number is not already assigned
            return number;
        else
            return inHouseID();
    }
    /**This method returns a random number for the Outsourced ID.
    * @return a random number that is not assigned to another number
    */
    public static int outsourcedID() {
        int number; // Holds the number
        int min = 30; // Sets the minimum to 30
        int max = 49; // Sets the maximum to 49
        Random rand = new Random(); // Calls for a new random number
        number = rand.nextInt(max - min) + min; // Assigns boundaries and random number to number
        if (selectPart(number) != true) // Verifies that the random number is not already assigned
            return number;
        else
            return outsourcedID();

    }
    /**This method returns a random number for the Product ID.
     * @return a random number that is not assigned to another number
     */
    public static int productID() {

        int number; // Holds the number
        int min = 50; //  Sets the minimum to 50
        int max = 70; // Sets the maximum to 70
        Random rand = new Random(); // Calls for a new random number
        number = rand.nextInt(max - min) + min; // Assigns boundaries and random number to number
        if(selectProduct(number) != true) // Verifies that the random number is not already assigned
            return number;
        else
            return productID();
        }
    }







