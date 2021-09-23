package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static model.Inventory.*;

/**This class is the controller for the MainForm. It controls all parts of the MainForm.FXML document
 ***********************************************************************************************************
 * FUTURE ENHANCEMENT
 * ADD an additional filter to the Product search engine to look up a product by associated parts
 */

public class MainFormController implements Initializable {

    Stage stage; // Holds the stage
    Parent scene; // Holds the scene

    @FXML
    // Assigns a TextField
    private TextField partSearchTxt;

    @FXML
    // Assigns to a TableView referencing a Part
    private TableView<Part> partsTableView;

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
    // Assigns a TextField
    private TextField productSearchTxt;

    @FXML
    // Assigns to a TableView referencing a Product
    private TableView<Product> productsTableView;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring an Integer
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring an Integer
    private TableColumn<Product, String> productNameCol;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring an Integer
    private TableColumn<Product, Integer> productInventoryCol;

    @FXML
    // Assigns to a TableColumn referencing a Part and declaring an Integer
    private TableColumn<Product, Double> productPriceCol;


    /**
     * This method is a search engine. It searches by partID or partName.
     * @param event is a search button
     ************************************************************************************************************
     * RUNTIME ERROR : NumberFormatException
     * I failed to put a try/catch block on the search feature. If no text was entered and the search button was
     * clicked, a Runtime Error occurred. I corrected this by adding the try/catch block with an ERROR alert.
     *************************************************************************************************************
     **/
    @FXML
    void onActionSearchParts(ActionEvent event) throws IOException {

        partsTableView.setItems(getAllParts()); // Sets allParts in the table for each search
        String name = partSearchTxt.getText(); // Creates a String to get the text to search

        try { // Test for errors
            if (Pattern.matches("[a-zA-Z]+", name)) { // Determines if Text is a String
                partsTableView.setItems(Inventory.lookupPart(name)); // Searches by partName; Returns if found
                if (getAllFilteredParts().isEmpty()) {  // Error dialog if part not found
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Part not found!");
                    alert.showAndWait();
                }

            } else {
                int id = Integer.parseInt(partSearchTxt.getText()); // Creates an Integer to store the text
                if (Inventory.selectPart(id) == true) { // Searches for part by ID
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
        // Catches exception
        catch (NumberFormatException e) {
            // Alert dialog box if no text is entered
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter text to be searched!");
            alert.showAndWait();
        }
    }
    /**
     * This method is a search engine. It searches by productID or productName.
     * @param event is a search button
     * */
    @FXML
    void onActionSearchProducts(ActionEvent event) throws IOException {

        productsTableView.setItems(getAllProducts()); // Sets allProducts in the table for each search
        String name = productSearchTxt.getText(); // Creates a String to get the text to be searched

        try { // Test for errors
            if (Pattern.matches("[a-zA-Z]+", name)) { // Determines if text is a String
                // Searches for the product and returns if found
                productsTableView.setItems(Inventory.lookupProduct(name));
                if (getAllFilteredProducts().isEmpty()) { // Error Dialog if product is not found
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Part not found!");
                    alert.showAndWait();
                }

            } else {
                int id = Integer.parseInt(productSearchTxt.getText()); // Creates an Integer the text
                if (Inventory.selectProduct(id) == true) { // Searches for the product by ID
                    productsTableView.getSelectionModel().select(lookupProduct(id)); // Returns product if found

                } else {
                    productsTableView.getSelectionModel().clearSelection(); // clears selector
                    Alert alert = new Alert(Alert.AlertType.ERROR); // Error Dialog if product not found
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Part not found!");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        productSearchTxt.clear(); // Clears text for next search

                    }
                }
            }
        }
        // To catch exception
        catch (NumberFormatException e) {
            // Alert Dialog box if no text is entered
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter text to be searched!");
            alert.showAndWait();
        }
    }
    /**This method loads the AddPart form in the event that the Add button is clicked
     * @param event is a button
     * */
    @FXML
    void onActionAddPartForm(ActionEvent event) throws IOException {
        // Loads and sets the scene for the AddPartForm
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This method loads the AddProduct form in the event that the Add button is clicked
     * @param event is a button
     * */
    @FXML
    void onActionAddProductForm(ActionEvent event) throws IOException {
        // Loads and sets the scene for the AddProductForm
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * This method deletes a part from the allPart list in the event that the delete button is clicked.
     * @param event is a button
     * */
    @FXML
    void onActionDeletePart(ActionEvent event) {
        // Alert dialog box to confirm deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the part, Do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait(); // To continue with the deletion
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem()); // Deletes selected part
            partsTableView.getSelectionModel().clearSelection(); // Clears the selector
        }
        partsTableView.getSelectionModel().clearSelection(); // Clears the selector
    }
    /**
     * This method deletes a product from the allProduct list in the event that the delete button is clicked.
     * @param event is a button
     * */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        // Alert Dialog box to confirm deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the part, Do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait(); // To continue with the deletion
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Creates a product to store the selection
            Product product = productsTableView.getSelectionModel().getSelectedItem();
            if (product.getAllAssocParts().isEmpty()) { // Ensures that there are no assocParts tied to the product
                Inventory.deleteProduct(productsTableView.getSelectionModel().getSelectedItem()); // Deletes the product
            }
            else
                JOptionPane.showMessageDialog(null, "Cannot delete a Product with" +
                        " Associated Parts!"); // Dialog box for products that have assocParts
                productsTableView.getSelectionModel().clearSelection(); // Clears selector
        }
        partsTableView.getSelectionModel().clearSelection(); // Clears selector
    }
    /**
     * This method Exits the application if the Exit button is clicked
     * @param event is a button
     * */
    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0); // Exits the application

    }
    /**
     * This method loads the ModifyPartForm if the button is clicked as well as passes the data selected to the
     * ModifyPartForm Controller.
     * @param event is a button
     *************************************************************************************************************
     * RUNTIME ERROR: classCastException. I was unable to load the modifyPartForm when selecting a part to modify and
     * clicking the Modify button. I received the classCastException. I checked for any typos but found none. I
     * did my research online but found nothing. I continued to build the application and hoped that I would discover
     * the solution later, and I did. I just happen to be looking at the ModifyPartForm.fxml when I noticed that the
     * fx:controller path was wrong. After correcting the path, the application worked as expected.
     * */
    @FXML
    void onActionModifyPartForm(ActionEvent event) throws IOException {

        try { // Used in the event of an exception
            FXMLLoader loader = new FXMLLoader(); // Loads scene for the ModifyPartForm
            loader.setLocation(getClass().getResource("/view/ModifyPartForm.fxml"));
            loader.load();
            // Creates a controller to load the ModifyPartForm Controller
            ModifyPartFormController MPFController = loader.getController(); //
            // Creates a part for the selection
            Part part = partsTableView.getSelectionModel().getSelectedItem();

            if(part.getPartID() <= 29) { // Separates the InHouseID's with the OutsourcedID's
                // Sends the InHouse part to the ModifyPartForm
                MPFController.sendPart((InHouse) partsTableView.getSelectionModel().getSelectedItem());
            }
            else { // Sends the Outsourced part to the ModifyPartForm
                MPFController.sendPart((Outsourced) partsTableView.getSelectionModel().getSelectedItem());
            }
            // Sets the scene for the ModifyPartForm
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        // Catches the exception
        catch (NullPointerException e){
            // Alert dialog box if no part has been selected to modify
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please Select a Part to Modify!");
            alert.showAndWait();
        }
    }
    /**
     * This method loads the ModifyProductForm if the button is clicked as well as passes the data selected to the
     * ModifyProductForm Controller
     * @param event is a button*/
    @FXML
    void onActionModifyProductForm (ActionEvent event) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(); // loads the scene for the ModifyProductForm
            loader.setLocation(getClass().getResource("/view/ModifyProductForm.fxml"));
            loader.load();
            // Creates a controller to load the ModifyProductForm Controller
            ModifyProductFormController MPrFController = loader.getController();
            // Sends the selected product to the ModifyProductForm
            MPrFController.sendProduct(productsTableView.getSelectionModel().getSelectedItem());
            // Sets the scene for the ModifyProductForm Controller
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            // Alert dialog box if not product is selected to modify
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please Select a Product to Modify!");
            alert.showAndWait();
        }
    }
    /**
     * This method is used to initialize any data when the form is loading and utilizes the @Override command.
     * @param  url to provide any pointer to the WWW if needed.
     * @param rb in the event of a local specific resource need*/

    @Override
    public void initialize(URL url, ResourceBundle rb) // assigns table column data
    {   // Returns allParts and sets the items in the partsTableView
        partsTableView.setItems(Inventory.getAllParts());
        // Assign partID to partIDCol
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        // Assigns partName to partNameCol
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        // Assigns partInStock to partInventoryCol
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        // Assigns partPrice to partPriceCol
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        // Returns allProducts and sets the items in the productTableView
        productsTableView.setItems(Inventory.getAllProducts());
        // Assigns the productId to the productIDCol
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        // Assigns the productName to the productNameCol
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        // Assigns the productInStock to the productInventoryCol
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("productInStock"));
        // Assigns the productPrice to the productPriceCol
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

    }
}


