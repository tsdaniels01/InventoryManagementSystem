package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static model.Inventory.*;

/**This class is the controller for the ModifyProductForm. It controls all parts of the ModifyProductForm.FXML document
 ***********************************************************************************************************
 * FUTURE ENHANCEMENT
 * Have a text field displaying the total sum of all associated parts. This will help the user determine if the
 * price of the product is greater than the price of the associated parts.
 ************************************************************************************************************
 */
public class ModifyProductFormController implements Initializable {

    Stage stage; // Holds the stage
    Parent scene; // Holds the scene
    // Holds all associated parts
    private ObservableList<Part> allAssocParts = FXCollections.observableArrayList();
    // Adds a part to the allAssocParts list
    public void addAssocPart(Part part) { allAssocParts.add(part);}
    // Returns allAssociatedParts list
    public  ObservableList<Part> getAllAssocParts() {return allAssocParts;}


    @FXML
    private TextField productIdTxt; // Assigns productIDTxt as a text field

    @FXML
    private TextField productNameTxt; // Assigns productNameTxt as a text field

    @FXML
    private TextField productInvTxt; // Assigns productInvTxt as a text field

    @FXML
    private TextField productPriceTxt;// Assigns productPriceTxt as a text field

    @FXML
    private TextField productMaxTxt; // Assigns productMaxTxt as a text field

    @FXML
    private TextField productMinTxt; // Assigns productMinTxt as a text field

    @FXML
    // Assigns to a TableView referencing a Part
    private TableView <Part> partsTableView;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring an Integer
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring a String
    private TableColumn<Part, String> partNameCol;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring an Integer
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring a Double
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    // Assigns to a TableView referencing a Part
    private TableView <Part> assocPartsTableView;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring an Integer
    private TableColumn<Part, Integer> assocPartIdCol;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring a String
    private TableColumn<Part, String> assocPartNameCol;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring an Integer
    private TableColumn<Part, Integer> assocPartInvCol;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring a Double
    private TableColumn<Part, Double> assocPartPriceCol;

    @FXML
    // Assigns the partSearchTxt as a text field
    private TextField partSearchTxt;

    /**
     * This method is a search engine. It searches by partID or partName.
     * @param event is a search button*/
    @FXML
    void onActionSearchPart(ActionEvent event) {

        partsTableView.setItems(getAllParts()); // Sets allParts in the table for each search
        String name = partSearchTxt.getText(); // Creates a String to get the text to search

    try { // Test for errors
        if (Pattern.matches("[a-zA-Z]+", name)) { // Determines if Text is a String
            partsTableView.setItems(Inventory.lookupPart(name)); // Searches by partName; Returns if found
            if (getAllFilteredParts().isEmpty()) { // Error dialog if part not found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Part not found!");
                alert.showAndWait();
            }

        } else {
            int id = Integer.parseInt(partSearchTxt.getText()); // Creates an Integer to store the text
            if (selectPart(id) == true) { // Searches for part by ID
                partsTableView.getSelectionModel().select(lookupPart(id)); // Returns the part if found

            } else {
                partsTableView.getSelectionModel().clearSelection(); // Clears the selector
                Alert alert = new Alert(Alert.AlertType.ERROR); // Error Dialog if not found
                alert.setTitle("Error Dialog");
                alert.setContentText("Part not found!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    partSearchTxt.clear(); // Clears text for next search

                }
            }

        }
    }
    // To catch exception
    catch (NumberFormatException e) {
        // Alert dialog box if no text is entered
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Please enter text to be searched!");
        alert.showAndWait();
    }

    }
    /**
     * This method adds a part from the partsTableView to the assocTableView when the add button is clicked.
     * @param event is a button
     *********************************************************************************************************
     * LOGICAL ERROR
     * I failed to put a dialog box warning if there was no part selected, because of that an empty row was added
     * to the table. I corrected this by adding an if else branch with a dialog box.
     *********************************************************************************************************
     * */
    @FXML
    void onActionAddAssocPart(ActionEvent event) {
        // To see if there is a selection made
        if (partsTableView.getSelectionModel().isEmpty()) {
            // Dialog box if no selection is made
            JOptionPane.showMessageDialog(null, "Please select a part to add!");
        }
        else {

            // Creates a part to hold the selected item
            Part part = partsTableView.getSelectionModel().getSelectedItem();
            // Adds the part to the associated parts list
            addAssocPart(part);
            // Clears the selector
            partsTableView.getSelectionModel().clearSelection();
            // Returns all associated parts and sets them in the assocPartsTableView
            assocPartsTableView.setItems(getAllAssocParts());
            // Assigns partID to assocPartIdCol
            assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
            // Assigns partName to assocPartNameCol
            assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
            // Assigns partInStock to assocPartInvCol
            assocPartInvCol.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
            // Assigns partPrice to assocPartPriceCol
            assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        }
    }
    /**
     * This method redirects to the MainForm if the cancel button is clicked.
     * @param event is a button
     * */
    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        // Loads and sets the scene for the mainForm
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
/**
 * This method removes an associated part from the assocPartsTableView if the button is clicked.
 * @param event is a button
 ****************************************************************************************************************
 * RUNTIME ERROR : NumberFormatException
 * I could not retrieve the id from the productIDTxt. I was trying to cast the textField to an Integer without
 * returning the String value of the textField.
 ****************************************************************************************************************
 * */
    @FXML
    void onActionRemAssocPart(ActionEvent event) {
        // Alert dialog box to confirm the removal
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will remove the part, Do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        // Removes the part if confirmed
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Creates an Integer to store the productID
            int id = Integer.parseInt(String.valueOf(productIdTxt.getText()));
            // Returns all parts
            for (Product product: getAllProducts())
                // Locates the part by productID
                if(product.getProductID() == id)
                    // Deletes the assocPart from the private list
                    product.deleteAssocPart(assocPartsTableView.getSelectionModel().getSelectedItem());
        }
        // Clears the selector
        assocPartsTableView.getSelectionModel().clearSelection();
    }

    /**
     * This method saves the newly created product if the button is clicked. It provides Alert dialog boxes to ensure
     * that the proper information is received.
     * @param event is a button
     * */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        // Tallies the total of all associated parts
        double cost; // Holds the cost
        double sum = 0; // Holds the sum
        int index = -1; // Index used for loop
        for (Part assocPart : getAllAssocParts()) { // get all associated parts
            cost = assocPart.getPartPrice(); // Returns the price of each part
            sum = sum + cost; // Adds all cost
            index++; // Iteration index
        }

        try { // Used in the event of an exception
            // Creates a new variable and stores the productIdTxt in it
            int id = Integer.parseInt(productIdTxt.getText());
            // Creates a new variable and stores the productNameTxt in it
            String name = productNameTxt.getText();
            // Creates a new variable and stores the productInventoryTxt in it
            int stock = Integer.parseInt(productInvTxt.getText());
            // Creates a new variable and stores the productPriceTxt in it
            double price = Double.parseDouble(productPriceTxt.getText());
            // Creates a new variable and stores the productMaxTxt in it
            int max = Integer.parseInt(productMaxTxt.getText());
            // Creates a new variable and stores the productMinTxt in it
            int min = Integer.parseInt(productMinTxt.getText());
            // Creates a list to store the assocTableView parts
            ObservableList assocPartsList = assocPartsTableView.getItems();
            // Ensures that the max Integer is greater than the minimum
            if (max < min) {
                // Alert dialog box when true
                Alert alert = new Alert(Alert.AlertType.ERROR, "The Maximum Inventory must be greater than the " +
                        "Minimum Inventory!");
                Optional<ButtonType> result = alert.showAndWait();
                // Clears text fields for new entry
                if (result.isPresent() && result.get() == ButtonType.OK)
                    productMaxTxt.clear();
                productMinTxt.clear();
            }
            // Ensures that the stock inventory is between the maximum and minimum inventory level
            else if (stock < min || stock > max){
                // Alert dialog box if true
                Alert alert = new Alert(Alert.AlertType.ERROR, "The Inventory level must be greater than or equal" +
                        " to the Minimum Inventory and less than or equal to the Maximum Inventory!");
                Optional<ButtonType> result = alert.showAndWait();
                // Clears text field for new entry
                if (result.isPresent() && result.get() == ButtonType.OK)
                    productInvTxt.clear();
            }
            // Ensures that the sum of the parts does not exceed the price of the product
            else if (sum > price){
                // Alert Dialog box if true
                Alert alert = new Alert(Alert.AlertType.ERROR, "The Total Price of the Parts cannot exceed the" +
                        " Price of the Product!");
                Optional<ButtonType> result = alert.showAndWait();

            }
            // Confirms that there are associated parts with the product before saving
            else if (getAllAssocParts().isEmpty()) {
                // Alert dialog box if false
                Alert alert = new Alert(Alert.AlertType.ERROR, "The Product must have Parts associated with it!");
                Optional<ButtonType> result = alert.showAndWait();
            }
            // Updates the modified product
            else {
                Inventory.updateProduct(id, new Product(id, name, price, stock, min, max, assocPartsList));
                // Confirms the save
                JOptionPane.showMessageDialog(null, "The product has been saved!");
                // Loads and sets the scene for the mainForm
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        }
        // Catches the exception
        catch (NumberFormatException e) {
            // Alert dialog box if text field values are incorrect
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter a valid value for each text field");
            alert.showAndWait();
        }

    }
    /**This method retrieves the product data sent by the mainForm.
     * @param product the product sent by the mainForm
     * */
    public void sendProduct(Product product)  {
        // Returns the product selected from the mainForm
        assocPartsTableView.setItems(product.getAllAssocParts());
        // Parses the productID and assigns to the productIDTxt
        productIdTxt.setText(String.valueOf(product.getProductID()));
        // Parses the productName and assigns to the productNameTxt
        productNameTxt.setText(product.getProductName());
        // Parses the productInStock and assigns to the productInvTxt
        productInvTxt.setText(String.valueOf(product.getProductInStock()));
        // Parses the partPrice and assigns to the partPriceTxt
        productPriceTxt.setText(String.valueOf(product.getProductPrice()));
        // Parses the productMin and assigns to the productMinTxt
        productMinTxt.setText(String.valueOf(product.getMin()));
        // Parses the productMax and assigns to the productMaxTxt
        productMaxTxt.setText(String.valueOf(product.getMax()));
        // Returns all associated parts
        allAssocParts = product.getAllAssocParts();

    }
    /**
     * This method is used to initialize any data when the form is loading and utilizes the @Override command.
     * @param  url to provide any pointer to the WWW if needed.
     * @param rb in the event of a local specific resource need*/
    @Override
    public void initialize(URL url, ResourceBundle rb) // assigns table column data
    {
        // Assigns allParts for the partsTableView
        partsTableView.setItems(Inventory.getAllParts());
        // Assign the partID and assigns to the partIDTxt
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        // Assign the partName and assigns to the partNameCol
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        // Assign the partInStock and assigns to the partInventoryTxt
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        // Assign the partPrice and assigns to the partPriceCol
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        // Assign the partID and assigns to the assocPartIDTxt
        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        // Assign the partName and assigns to the assocPartNameCol
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        // Assign the partInStock and assigns to the assocPartInvCol
        assocPartInvCol.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        // Assign the partPrice and assigns to the assocPartPriceCol
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
    }

}





